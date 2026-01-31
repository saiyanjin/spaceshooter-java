# Space Shooter OpenGL - Instructions

## Description
Un space shooter 3D simple créé avec OpenGL et JOGL en java

## Fichiers du projet
- **SpaceShooterMain.java** : Classe principale du jeu
- **Player.java** : Vaisseau du joueur
- **Alien.java** : Aliens modélisés en 3D
- **Bullet.java** : Projectiles tirés par le joueur
- **Particle.java** : Particules pour les explosions
- **ParticleSystem.java** : Système de gestion des particules

## Contrôles
- **Flèches directionnelles gauche droite** ou **Q/D** : Déplacer le vaisseau à l'horizontale, il est possible d'ajouter le déplacement vertical en décommentant les 2 lignes correspondantes dans Player.java (**haut/bas** ou **Z/S**)
- **ESPACE** : Tirer
- **R** : Redémarrer la partie (quand Game Over)

## Fonctionnalités
✓ Vaisseau joueur contrôlable au clavier
✓ Aliens modélisés en 3D avec rotation
✓ Système de particules pour les explosions
✓ Détection de collision
✓ Game Over quand les aliens atteignent le bas ou touchent le joueur

## Installation dans Eclipse
1. Besoin de JOGL
2. Lancer SpaceShooterMain.java
3. Ajouter **--add-exports java.base/java.lang=ALL-UNNAMED --add-exports java.desktop/sun.awt=ALL-UNNAMED --add-exports java.desktop/sun.java2d=ALL-UNNAMED** dans VM arguments à la configuration de lancement si ça ne fonctionne pas directement
