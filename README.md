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

ou veuillez extraire l'archive .tar.gz

```
tar -zxvf catteze-vastraa-da2i-boggle.tar.gz
```

Assurez-vous que vous avez les droits d'exécution sur les scripts **compiler.sh** et **run.sh**

```
chmod +x compiler.sh run.sh
```

Pour compiler les sources, exécutez le script suivant :

```
./compiler.sh
```

Pour exécuter le programme à partir de sources, exécutez le script suivant :

```
./run.sh
```

Pour exécuter le programme depuis l'archive exécutable da2i-boggle.jar, exécutez la commande suivante :

```
java -jar da2i-boggle.jar
```

## Manuel utilisateur

Afin de décrire au mieux l'ensemble des possibilitées offertes par le programme, le manuel utilisateur sera développé au travers d'un exemple concret et lu par le biais d'un accent marseillais.

Voici Tony, un joueur passionné de boggle depuis de nombreuses années à qui nous avons proposé de tester notre version du jeu, en avant première. Lors du premier lancement de l'application, le visage de Tony resta impassible, nullement impressionné par la première image du jeu. Toutefois, l'ergonomie de l'application fit que Tony dirigea sa souris naturellement et rapidement vers les boutons situés en bas de l'écran, à savoir "Jouer" et "Classements". Il cliqua sur "Jouer". Nous imaginons que n'ayant pas d'intérêt compétitif dans ce test, le classement n'intéressait pas (encore) ce grand champion boggelien qu'est Tony.

![Menu Principal](https://cloud.githubusercontent.com/assets/10498113/11850272/95c2e488-a42c-11e5-8eff-37fa29696dc0.png)

L'arrivée sur le second écran, celui de la création de la partie, parvint a détendre le visage de Tony. En effet, nous pouvions voir les prémisses d'un sourire naitre à la commissure de ses lèvres. La définition du nombre de joueurs (humain ou non) par exemple ou encore les nombreuses possibilités de réglages selon lesquelles la partie se déroulerait, le nombre de tour de jeu, le temps accordé pour chaque joueur et bien plus encore; ont fait écho quelque part dans l'âme de ce compétiteur acharné, toujours à la recherche de challenges. Un bon point pour le jeu.

![Création de Partie](https://cloud.githubusercontent.com/assets/10498113/11850273/95c54818-a42c-11e5-8455-9b065496c889.png)

Plusieurs minutes plus tard, notre testeur choisit ainsi de se frotter à notre intelligence artificielle, Samantha, et ce, sans échauffement préalable. Intelligence artificielle qui a été pensée en partie pour offrir une farouche opposition à n'importe quel joueur, allant du novice au champion.

La première grille de mots ne fut pas à l'avantage du champion puisque la face visible de chaque dé n'offraient pas un assemblage de lettres des plus évidents.

![Partie de jeu](https://cloud.githubusercontent.com/assets/10498113/11850274/95c9c0a0-a42c-11e5-9430-3dbd9f796c4d.png)

Cependant contre toute attente, Tony réussit à écrire manuellement un nombre impressionnant de mots, allant du simple 3 lettres jusqu'à certains mots inconnus des membres de notre équipe de développement. Il poussa même le vice a obtenir plus de points que Samantha lors de ce premier tour de jeu, qui ne dura pas plus de 40 secondes. Nous sommes restés sans voix devant tant de maîtrise de la langue. Le champ de saisie des mots au clavier avait offert à Tony une rapidité d'éxecution quasiment sans limite !

Le deuxième tour commença et à la surprise de tous, Tony empoigna la souris afin d'offrir un aperçu de sa fameuse technique "la danse du curseur". Les différentes cases de la grille s'allumèrent au rythme infernal des cliques aussi bien sur les dés, que sur le bouton "Ajouter". De plus, le testeur se permit de terminer son tour de jeu avant la fin du temps imparti. Notre Samantha n'était-elle pas au niveau ? Tony voulait-il simplement s'handicaper intentionnellement pour tester sa vitesse d'éxecution sous la pression d'une possible défaite ? Ou la grille actuelle n'avait-elle simplement plus de mots possibles ? Autant de questions qui se bousculaient dans la tête de chacun, tandis que Samantha sous le regard autain du champion entamma son tour de jeu. 

Cette échange acharné durera de longues minutes, jusqu'à la dernière seconde et à l'ajout du mot "immarcescible" de Samantha. Ce dernier mot qui lui assura la victoire sur le fil. Le score de 1000 à 999 en faveur de Samantha aurait pu achever la détermination de n'importe quel autre personne. Mais un champion n'est pas n'importe quelle personne. C'est ainsi que Tony se leva de sa chaise et quitta la salle de TP avec le même sourire qu'un enfant ayant découvert un nouveau jeu, et en murmurant suffisament bas pour que nous soyons les seuls à l'entendre : " GG ".

![Classements Généraux](https://cloud.githubusercontent.com/assets/10498113/11850275/95cb2972-a42c-11e5-9b33-cc4c8af2bddb.png)
