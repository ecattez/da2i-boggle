# Boggle - Projet de Génie Logiciel (Licence DA2I)

## Auteurs
- Edouard CATTEZ
- Alexandre VASTRA

## Table des Matières

- Fonctionnalités
- Installation
- Manuel utilisateur


##Fonctionnalités
- [x] Multijoueur avec joueurs Humain et IA
- [x] Intelligence Artificielle
- [x] Partie avec score maximal à atteindre
- [x] Partie avec tour de jeu maximal
- [x] Score et tour max avec possibilité de rendre illimité soit le score, soit le tour
- [x] Sablier pour le tour d'un joueur
- [x] Changement d'état des dés (utilisé ou non)
- [x] Ecrire un mot et vérifier son existence dans la grille en temps réel
- [x] Mémoriser les meilleurs scores par taille de grille
- [x] Utiliser des fichier de dés
- [x] Utiliser des fichiers de dictionnaire
- [x] Utiliser un fichier de configuration pour choisir la taille de la grille, le dictionnaire, les dés, les points etc...
- [ ] Aide contextuelle pour la recherche de mots valides dans la grille

## Installation

Téléchargez le répertoire git

```
git clone https://github.com/ecattez/da2i-boggle.git
```

ou veuillez extraire l'archive .jar

```
tar -zxvf catteze-vastra-da2i-boggle.tar.gz
```

Assurez-vous que vous avez les droits d'exécution sur les scripts compiler.sh et run.sh

Pour compiler les sources, exécutez le script suivant :

```
./compiler.sh
```

Pour exécuter le programme à partir de sources, exécutez le script suivant :

```
./run.sh
```

Pour exécuter le programme depuis l'archive exécutable da2i-boggle.jar, exécuter la commande suivante :

```
java -jar da2i-boggle.jar
```


## Manuel utilisateur
Afin de décrire au mieux l'ensemble des possibilitées offertent par le jeu, ce manuel utilisateur les exposera au travers d'un exemple concret.

Voici Tony, un joueur passionné de boggle depuis de nombreuses années. C'est pourquoi notre équipe a décidé de lui proposé de tester sa vision du jeu en avant première. Lors du premier lancement de l'application, et l'apparition de l'écran principal du jeu, le visage de Tony reste impassible, nullement impressionné par la première image du jeu, ou encore son ergonomie. Puisqu'en effet Tony c'est naturellement et rapidement dirigé vers les boutons situés en bas de l'écran, a savoir "Jouer" et "Classements", pour sélectionner naturellement "Jouer". J'imagine que n'ayant pas d'intérêt compétitif dans ce test le classement n'intéresse pas ce grand champion boggelien qu'est Tony.

L'arrivée sur le second écran, celui la personnalisation de la partie, détend parvient a détendre le visage de Tony, en effet je peux voir les prémisses d'un sourire naitre à la commissure de ses lèvres. J'imagine que les nombreuses possibilitées de réglages dans le nombre de joueurs ( humain ou non) par exemple ou encore les règles selon lesquelles la partie va se déroulé, le nombre de tour de la partie , le temps accordé pour chaque joueurs et bien plus encore; Ont fait écho quelque part dans l'âme de ce compétiteur acharné toujours à la recherche de challenges. Un bon point pour le jeu.

Plusieurs minutes plus tard, notre testeur décide ainsi de se frotter à notre intelligence artificielle, Samantha, et ceux sans échauffement. Intelligence artificielle qui a été pensé en partie pour offrir une opposition farouche à n'importe quel joueur, allant du novice au champion. 
La partie se lance, la première grille n'est pas à l'avantage du champion en effet les faces visiblent de chaque dés, misent en commun, ne sont pas dé plus évidente. Cependant contre toutes attentes Tony parvient à écrire manuellement un nombre impressionnant de mots, allant du simple 3 lettres jusqu'à certains mot inconnu de certains membres de l'équipe de développement. Il pousse même le vice a obtenir plus de points que Samantha lors de ce premier tour de jeu, et celui-ci n'a duré que 40s. L'équipe complète reste sans voix devant tant de maitrise de la langue et la rapidité d'execution. Le deuxième tour commence et à la surprise de tous Tony, empoigne la souris et au yeux de tous offre un aperçu de sa fameuse technique  "la danse du curseur", les différentes cases de la grille s'allume au rythme infernal des "ajouter". De plus le cobaye se permet de terminer son tour de jeu avant la fin du temps impartie. Notre Samantha n'est-elle pas au niveau ? Veux-t-il simplement s'handicaper intentionnellement pour tester sa vitesse d'execution sous la pression d'une possible défaite ? Ou la grille actuelle n'avait-elle simplement plus de mots possible ? Autant de questions qui se bousculent dans la tête de chacun, tandis que Samantha sous le regard autain du champion entamme son tour de jeu. 
Cette échange acharné durera plusieurs longue minutes, jusqu'à la dernière seconde et le dernier ajout de Samantha du mot " bite". Ce dernier mot qui lui assura la victoire sur le fil, en effet le score de 1000 à 999 en faveur de Samantha aurait pu achever la dertemination de n'importe quel autre personne, mais un champion n'est pas n'importe quelle personne. C'est ainsi que Tony se lèva de sa chaise et quitta la pièce de test avec le même sourire qu'un enfant qui découvre un nouveau jeu et  en murmurant suffisament bas, pour que je sois le seul à l'entendre : " Good Job ".



