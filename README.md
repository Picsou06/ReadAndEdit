# ReadAndEdit

Plugin Minecraft (Spigot/Paper, API 1.20) permettant de sauvegarder et de restaurer le contenu des livres écrits par les joueurs (`PlayerEditBookEvent`), avec une persistance en base de données MySQL.

Écrit pour un projet Danganronpa-Minecraft (serveur discord.gg/Dreamin), avant tout comme entraînement au développement de plugins.

## Stack technique

- Java
- API Bukkit/Spigot (1.20)
- MySQL (via `DatabaseManager`)
- Gradle

## Fonctionnement

- `EditBookEvent` intercepte les modifications de livre d'un joueur et sérialise le `BookMeta` pour le sauvegarder.
- `OnPlayerLoginEvent` restaure les livres du joueur à sa connexion.
- `CommandGiveBook` permet de donner un livre sauvegardé.
- `DatabaseManager` gère la connexion et les requêtes MySQL.

## Installation

1. Compiler avec Gradle (`./gradlew build`).
2. Configurer l'accès MySQL dans `src/main/resources/config.yml`.
3. Placer le `.jar` dans `plugins/` d'un serveur Spigot/Paper 1.20 et redémarrer.

## Structure

```
src/main/java/fr/picsou/readandedit/
├── Main.java
├── mysql/DatabaseManager.java
├── components/commands/CommandGiveBook.java
└── components/listener/Player/
    ├── EditBookEvent.java
    └── OnPlayerLoginEvent.java
```
