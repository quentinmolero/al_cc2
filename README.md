# al_cc2

Projet CC2 d'AL par Quentin MOLERO, qmolero@myges.fr

# Lancement de l'application

Pour lancer l'application, il existe deux moyens :
 - Le premier est de passer par la class `Main.java` en exécutant la méthode `main`. L'application sera alors lancée en mode console.
 - Le deuxième est de passer par la class `SpringMain.java` en exécutant la méthode `main`. L'application se lancera en mode web et il sera possible d'interagir avec via les routes.

# Routes Spring

Différentes routes de l'application

| Route         | Methode |  Paramètre   |                Corps                 | Résultat                                                                                     |
|---------------|:-------:|:------------:|:------------------------------------:|----------------------------------------------------------------------------------------------|
| /users        |  `GET`  |      NA      |                  NA                  | Renvoie la liste de tout les utilisateurs                                                    |
| /user         |  `GET`  |   `userId`   |                  NA                  | Renvoie l'utilisateur identifié par le `userId`                                              |
| /user         | `POST`  |      NA      | `firstname`, `lastname`, `password`  | Créer un utilisateur si les champs passés dans le corps sont valides                         |
| /payments     |  `GET`  |      NA      |                  NA                  | Renvoie la liste de tout les paiements                                                       |
| /payment      |  `GET`  | `paymentId`  |                  NA                  | Renvoie le paiement identifié par le `paymentId`                                             |
| /payment      | `POST`  |      NA      |          `amount`, `userId`          | Créer un paiement si les champs passés dans le corps sont valides                            |
| /userPayments |  `GET`  |      NA      |                  NA                  | Renvoie la liste de tout les utilisateurs avec la liste des paiements qui leur sont associés |
| /userPayment  |  `GET`  |   `userId`   |                  NA                  | Renvoie la liste des paiements associé à l'utilisateur identifié par le paramètre `userId`   |

Vous trouverez sur myges un export des requêtes POSTMAN pour pouvoir tester plus facilement les routes.

---
# Implémentation

## Généralités

L'application nécessite un système d'utilisateur et un système de paiement. Dans mon implémentation, les utilisateurs seront des `User`, et les paiements des `Payment`. 

L'association entre mes `User` et `Payment` se fera via une instance de type `UserPaymentRepository`. L'implémentation de cette classe est actuellement `InMemoryUserPaymentRepository.java`, qui stock *InMemory* un objet de type `Map<UserId, List<PaymentId>>`.

Le montant des paiements est enregistré sur une variable de type `long` et non `double` afin de contourner le problème d'arrondis en JAVA. Les prix sont donc en centimes.

Par rapport à la consigne révisée, le contenu implémenté est :

 - [x] Renforcement de la mise en avant de l’intention métier
 - [x] Reprise des bonnes pratiques Objet
 - [ ] Gestion fine des erreurs métiers
 - [x] Respect des packages et de la séparation des responsabilités
 - [x] Ajout de contrôleurs Web REST pour chaque grand cas d’utilisation

Bonus
 - [x] Introduction dans le code du pattern CQS
 - [ ] Mise en place d’une architecture SEDA
 - [ ] Ajout de la couverture des tests : des tests unitaires + des tests Web

J'ai essayé d'implémenter un système de gestion fine des erreurs, cependant je pense m'y être pris trop tard et je n'ai pas trouvé une implementation correcte et propre sans avoir à recoder une grosse partie de l'application. J'espère que ce manque sera comblé par le modèle CQS. 

## Les services
Les services sont au nombre de 3 et permettent de gérer l'interaction entre les repository et le code de l'application. Nous retrouvons les services suivants :
 - `UserService.java` 
   - `create()` Créer un nouvel utilisateur et l'enregistre
   - `changePassword()` Modifie le mot de passe de l'utilisateur identifié par le paramètre `userId`
   - `all()` Liste tous les utilisateurs enregistrés
 - `PaymentService.java`
   - `create()` Créer un nouveau paiement
   - `all()` Liste tous les paiements enregistrés
 - `UserPaymentService.java`
   - `create()` Permet de créer une nouvelle association afin d'ajouter un `Payment` à un `User`. La méthode est surchargée pour qu'elle soit utilisable depuis le mode console et le mode web avec Spring
   - `all()` Liste de toutes les associations entre les `User` et les `Payment`
 
## Système d'évènements
Lors de la création d'un nouvel utilisateur, un système de service lance automatiquement la création d'un nouveau paiement.

Le déclenchement de l'évènement se fait lorsque la méthode `create()` de la classe `UserService` est appelée. Un `ApplicationEvent` de type `UserStartSubscription(User user)` est publié, il est ensuite intercepté et déclenche un appel à la méthode `listenTo()` de la classe `UserSubscriptionListener`.

L'activation de l'évènement va créer un nouvel objet `Payment`, puis l'ajouter à la liste des paiements de l'utilisateur concerné dans le `UserPaymentRepository`

## Systèmes d'état
Un système d'état sur les utilisateurs permet de savoir où en est la création d'un utilisateur. Il existe 3 états possibles, définis dans par l'énumération `UserState` :
 - `IN_CREATION` L'utilisateur est en cours de création
 - `PENDING` La création de l'utilisateur est en attente
 - `CREATED` La création est terminée

Ce système permet à un utilisateur de créer étape par étape la création de son compte, mais aussi de la reprendre plus tard. L'historique des états est stocké dans le champ de type `UserStateHistory`, qui permet d'obtenir l'historique sous forme de liste.

## Validation des objets
Les objets de type `User` et `Payment` sont soumis à une validation de leurs champs lors de leur création. Cette validation se fait en passant par un `Engine`, nous en retrouvons donc deux :
 - `ValidationUserEngine` Valide les champs propre à un utilisateur
 - `ValidationPaymentEngine` Valide les champs propre à un paiement

Ces deux moteurs de validations implémentent la classe `Predicate` de JAVA, ce qui permet de produire une méthode `test()` pour la validation.

## Renouvellement mensuel des paiements
Le renouvellement mensuel des paiements se fait de manière automatique via la classe `MonthlyPaymentEngine`. Cette classe qui extends `Thread` de JAVA vas lancer un nouveau Thread afin de ne pas bloquer le fil d'exécution lors de son appel. 

Une fois lancé, il va récupérer tous les utilisateurs et la date de leur dernier paiement. Si ce dernier est supérieur à 30 jours, alors un nouveau paiement est fait.

La vérification se met ensuite en "sommeil" pour 24 h, ainsi nous vérifions 1 fois par jour que les abonnements sont bien renouvelés.

## Pattern CQS
Pour les routes Spring, j'ai utilisé le modèle Command Query Separation.

Prenons l'exemple de la route `/users` définit dans la classe `UserController` :

1. Lorsqu'une requête `GET` est faite sur cette route, une nouvelle instance de `RetrieveUsers` est envoyé dans mon bus, il s'agit de la Query.
2. La Query se fait ensuite intercepté par `RetrieveUsersHandler`, qui vas s'occuper de récupérer la liste des utilisateurs et la renvoyer
3. La liste est ensuite formatée à un format exposé à l'utilisateur puis est envoyé en réponse à la requête

Cet exemple retrace le cas d'une Query, pour les Commandes, le fonctionnement est le même sur le fond. La différence majeure est au niveau du bus, il existe un bus pour les Query et un pour les Commandes.