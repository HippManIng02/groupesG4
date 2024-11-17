# GroupesG4 - Guide du projet

Ce document explique comment compiler le projet, liste les dépendances nécessaires et décrit la configuration de la connexion à la base de données.

## Prérequis

Avant de commencer, assurez-vous que les éléments suivants sont installés sur votre machine :

- **Java Development Kit (JDK)** : Version 21.
- **Apache Maven** : Utilisé pour gérer les dépendances et compiler le projet.

## Dépendances

Les principales dépendances du projet sont gérées par Maven et sont définies dans le fichier `pom.xml`. Voici les dépendances clés :

- **MySQL Connector/J** : Driver JDBC pour se connecter à la base de données MySQL.
- **Junit** : Framework de test pour les tests unitaires.

Maven téléchargera automatiquement toutes les dépendances lors de la première compilation.

## Compilation du Projet

Pour compiler et générer le fichier `.jar` exécutable du projet, suivez les étapes ci-dessous :

1. **Cloner le Projet**
   Récupérez les sources du projet en clonant le dépôt Git ou en copiant les fichiers dans votre environnement local.

   ```bash
   git clone <url-du-dépôt>
   cd <nom-du-dossier-du-projet>
   
2. **Nettoyer les Fichiers de Compilation Précédents**
Exécutez la commande suivante pour supprimer les fichiers de compilation précédents :
- **mvn clean**

3. **Compiler le Projet**
Compilez le code source Java avec Maven :
- **mvn compile**
4. **Générer le Fichier .jar Exécutable**
Utilisez la commande package pour créer le fichier .jar exécutable dans le dossier target/ :
- **mvn package**
Le fichier .jar généré se trouve dans le dossier target  ``groupesG4-1.0-SNAPSHOT.jar``.

5. **Configuration de la Base de Données**
Le projet utilise MySQL comme base de données. Assurez-vous que votre instance MySQL est configurée et que les informations de connexion sont correctes.

- **Informations de Connexion**
Les informations de connexion à la base de données sont stockées dans un fichier de configuration (database.properties et donnez le chemin vers ce fichier dans la variable d'environnement DB_CONFIG_PATH). Mettez à jour les valeurs suivantes avec vos informations MySQL :
  ``URL: jdbc:mysql://localhost:3306/groupes
   Utilisateur: marcpetit
   Mot de passe: mdp-marcpetit
   Port: 3306`
## Exécution du Projet
Une fois le projet compilé, vous pouvez exécuter le fichier .jar en utilisant la commande suivante :
    ```bash
    java -jar target/groupesG4-1.0-SNAPSHOT.jar




## Quelques commandes utiles
 - git clone url
 - git fetch --all
 - git checkout develop
 - git branch ma_branche_de_dev
 - git checkout ma_branche_de_dev
 - git add .
 Méthode personnel pour pusher
 - git stash
 - git pull origin develop
 - git stash apply
 Ensuite résolution de conflit en local s'il y en a
 - git add .
 - git commit -m "Message"
 - git push origin ma_branche_de_dev
 Et je fais le merge en ligne avec la branche develop

## Enfinnnn

 Bon codage et bonne chance à nous sur ce projet. Prenons du plaisir dans le code.
