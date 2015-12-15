# Boggle - Projet de Génie Logiciel (Licence DA2I)

## Auteurs

- Edouard CATTEZ
- Alexandre VASTRA

## Table des Matières

- Fonctionnalités
- Installation
- Manuel utilisateur


##Fonctionnalités

- Multijoueur avec joueurs Humain et IA
- Intelligence Artificielle
- Partie avec score maximal à atteindre
- Partie avec tour de jeu maximal
- Score et tour max avec possibilité de rendre illimité soit le score, soit le tour
- Sablier pour le tour d'un joueur
- Changement d'état des dés (utilisé ou non)
- Ecrire un mot et vérifier son existence dans la grille en temps réel
- Mémoriser les meilleurs scores par taille de grille
- Utiliser des fichier de dés
- Utiliser des fichiers de dictionnaire
- Utiliser un fichier de configuration pour choisir la taille de la grille, le dictionnaire, les dés, les points etc...

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

Afin de décrire au mieux l'ensemble des possibilitées offertes par le programme, le manuel utilisateur sera développé au travers d'un exemple concret et lu par le biais d'un accent marseillais.

Voici Tony, un joueur passionné de boggle depuis de nombreuses années. C'est pourquoi nous avons décidé de lui proposé de tester notre version du jeu en avant première. Lors du premier lancement de l'application, et l'apparition de l'écran principal du jeu, le visage de Tony resta impassible, nullement impressionné par la première image du jeu, ou encore son ergonomie. Puisqu'en effet Tony c'est naturellement et rapidement dirigé vers les boutons situés en bas de l'écran, à savoir "Jouer" et "Classements". Il cliqua sur "Jouer". Nous imaginons que n'ayant pas d'intérêt compétitif dans ce test, le classement n'intéressait pas (encore) ce grand champion boggelien qu'est Tony.

L'arrivée sur le second écran, celui de la création de la partie, parvient a détendre le visage de Tony. En effet, nous pouvons voir les prémisses d'un sourire naitre à la commissure de ses lèvres. La définition du nombre de joueurs (humain ou non) par exemple ou encore les nombreuses possibilitées de réglages selon lesquelles la partie va se dérouler, le nombre de tour de la partie, le temps accordé pour chaque joueur et bien plus encore; ont fait écho quelque part dans l'âme de ce compétiteur acharné, toujours à la recherche de challenges. Un bon point pour le jeu.

Plusieurs minutes plus tard, notre testeur décide ainsi de se frotter à notre intelligence artificielle, Samantha, et ce, sans échauffement préalable. Intelligence artificielle qui a été pensée en partie pour offrir une farouche opposition à n'importe quel joueur, allant du novice au champion.

La partie se lance, la première grille n'est pas à l'avantage du champion. En effet, les faces visibles de chaque dé, misent en communs, ne sont pas des plus évidentes.

Cependant contre toutes attentes, Tony parvient à écrire manuellement un nombre impressionnant de mots, allant du simple 3 lettres jusqu'à certains mots inconnus des membres de notre équipe de développement. Il pousse même le vice a obtenir plus de points que Samantha lors de ce premier tour de jeu, qui n'a duré que 40s. Nous restons sans voix devant tant de maîtrise de la langue et la rapidité d'éxecution via le champ de saisie qui lui permet d'écrire les mots plus vite que s'il cliquait sur les dés de la grille.

Le deuxième tour commence et à la surprise de tous, Tony empoigne la souris et offre un aperçu de sa fameuse technique "la danse du curseur". Les différentes cases de la grille s'allument au rythme infernal des cliques aussi bien sur les dés, que sur le bouton "Ajouter". De plus, le testeur se permet de terminer son tour de jeu avant la fin du temps imparti. Notre Samantha n'est-elle pas au niveau ? Tony veut-il simplement s'handicaper intentionnellement pour tester sa vitesse d'éxecution sous la pression d'une possible défaite ? Ou la grille actuelle n'avait-elle simplement plus de mots possibles ? Autant de questions qui se bousculent dans la tête de chacun, tandis que Samantha sous le regard autain du champion entamme son tour de jeu. 

Cette échange acharné durera de longues minutes, jusqu'à la dernière seconde et à l'ajout du mot "bière" de Samantha. Ce dernier mot qui lui assura la victoire sur le fil. Le score de 1000 à 999 en faveur de Samantha aurait pu achever la détermination de n'importe quel autre personne. Mais un champion n'est pas n'importe quelle personne. C'est ainsi que Tony se leva de sa chaise et quitta la salle de TP avec le même sourire qu'un enfant qui découvre un nouveau jeu et en murmurant suffisament bas, pour que nous soyons les seuls à l'entendre : " Good Job ".



