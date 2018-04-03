TP2 Repast: Predator Pray

Le système a été conçu de la manière suivante:

- A l'initialisation, chaque case reçoit un agent (Un loup, un mouton, une herbe vivante ou bien une herbe morte)
- A chaque tick, un animal (Wolf ou Sheep) effectue un mouvement, puis mange s'il y a quelque chose à manger dans la case de destination puis donne naissance à un enfant de la même espèce dès qu'il atteint son seuil énergitique de procréation
- L'énergie est décrementée avant le début du mouvement, et seulement si il y a un mouvement réel (une translation de vecteur nul ne décremente pas l'énergie)
- Le loup ne peut manger que des moutons; le mouton ne peut manger que des herbes VIVANTES (si un mouton se rend dans une case où l'herbe est morte, il ne peut pas la manger)
- l'herbe morte se régénère ((re)passe à l'état vivant) au bout de n "ticks"
- l'herbe passe de l'état vivant à morte si elle est mangée


Etat de fonctionnement du système:

Ce qui fonctionne bien:
-La régénération de l'herbe
-Le repas des loups et des moutons
-La disparition des loups et des moutons de l'interface quand ils meurent
-l'apparition des loups et moutons nouveaux-nés
-Le déplacement des loups et moutons

Ce qui ne fonctionne pas:
-L'affichage dynamique de l'herbe (changement de couleur vert jaune selon que l'herbe est vivante (verte) ou morte (jaune)):
    - Le display par défaut présente les herbes avec des points verts (quelque soit leur état vivant ou mort)
    -Nous avons essayé d'ajouter un Continous Space et une classe de style "GrassStateStyle" pour l'herbe: le changement de couleur fonctionne mais les points d'herbe sont mal positionnés sur l'interface graphique
