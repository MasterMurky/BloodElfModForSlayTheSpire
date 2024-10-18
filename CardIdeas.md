# IDÉES DE CARTES À CRÉER

## Perte de PV
* ~~power : chaque fois qu'on prend des dégâts : deal 4 to a random ennemy (gain 3 armor)
  --> heal 1 (cout 2)
  --> gain 1 strenght (cout 3)   --> gain 1 temp. dext (cout 1) (fusionnés)~~
- ~~lose 1 HP, gain 5 armor, do these actions two (three) times times~~
* Inflige des dégâts aux adversaires égaux aux dégâts que vous avez pris cette game
* ~~fromage : heal de ... --> comparer à réparation du defect~~
* ~~1 - Gagne 5(7) d'armure. Si vous avez subi au moins 1X des dégâts pendant ce tour, vous soigne de 3(4).~~
* Acceptation du destin : perso qui ouvre grand les bras et qui s'apprête à mourir brûlé--> se met à 1 PV mais ne peut pas mourir pendant 2 tours, se heal de X à la fin du combat
* ~~inflige 1 dégât à ton perso, 4 à un ennemi aléatoire et te rend 1 PP (amelioré : et pioche 1 carte?) si t as déjà subi 1 dégât ce tour.~~
* ~~Prend 1 dégât 3X, heal de 5 (7)~~
* ~~carte injouable qui s'active lorsque tu perds X PV pendant ton tour. Genre 5.~~
* infliger X(Y) dégâts à un ennemi. Se soigne de la moitié des dégâts infligés.
* ~~0 : perdre 2PV, piocher une(2) carte.~~
* 1 - Apothéose : Si vous avez subi des dégâts 3X pendant ce tour, inflige 12(15) dégâts à tous les ennemis.
* Si t'es affaibli, inflige + de dégâts.


## Buffs temporaires et annulation de l'effet temporaire
Idée : améliorer sa force de façon significative en se donnant des buffs temporaires puis en enlevant l'effet temporaire (débuff). Ensuite, jouer des cartes qui infliges 2 dégâts 5X pour rentabiliser la force. Ou cartes dont l'effet de force est dupliqué.
* ~~1 - mets dans votre main une (2) copie de la dernière carte que vous avez joué, la copie prend épuisement. éthéré & épuisement~~
* ~~1 (0) - pioche 1 carte, gagne 1 de force temporaire par carte piochée ce tour~~
* 1/2? - annule vos débuffs 2 puis (1) ? upgrade?? (fort car annule aussi les débuffs des ennemis - carte rare car pas besoin de plein de copies
*  retour à zéro : 2 - annule vos débuffs, pioche 1 par débuff retiré
* 0 - tout le monde prend 1 dégât, tout le monde gagne 1 de dext/force?
* 0 - Bâton tordu : Inflige 1 dégât 3X, pioche 1 carte
* 1 - Mec musclé genre solide qui flex : Gagne autant d'armure que le montant (double) de votre force.
* carte qui te donne autant de force que la force temporaire que tu as actuellement. calculer la force actuelle moins le débuff de force temporaire.


[Regeneration.java](src%2Fmain%2Fjava%2Fbloodandhell%2Fcards%2FPowers%2FRegeneration.java)



## Jouer tout le temps la même carte et survivre
* 1 - mets dans votre main une (2) copie de la dernière carte que vous avez joué, la copie prend épuisement. éthéré & épuisement
* 1 - transforme cette carte en une copie d'une carte de ta défausse (défausse ou pioche)
* la même chose que la carte qui commence à 0 et augmente les dégâts mais pour l'armure/ les cartes piochées ?
* une carte qui se dédouble quand tu la pioches : comme endless agony (jeu de base)






## Trucs randoms / Sans catégorie
* checker la carte "Blizzard" pour la carte qui met des dégâts qui valent le nbr de PV manquants /4
* rendre inné le pouvoir de shield lié à la perte de PV et celui des dégâts.
* carte blood 4 blood
* carte choix : shield ou atk ennemi (random?) (voir carte existante Wish)
* carte de cout X
* ~~petits plaisirs/tout en un : 2 - draw 1, inflige 6(9) dégâts à un ennemi aléatoire, gagne 5/8 d'armure, soigne de 4, gagne 10 d'or. épuisement~~
* 1 - Creux intérieur (personnage avec creux noir genre trou au milieu de son corps ) : inflige des dégâts à un ennemi équivalents à ses PV manquants divisés par 5 (4) - maximum de 30 dégâts (pour éviter les abus sur le coeur par exemple)
* joue les cartes du haut de son paquet genre les 2 (3) du dessus - mesures d'extrême urgence 
* Sacrement d'un roi (?) nom stylé xD - gagne 10 d'or et le statut couronné : chaque ennemi tué pendant le combat donne 5 d'or. couronne qui descend sur la tête comme effet visuel + bruit de trompettes
* Code moral écarlate --> vanish une carte de sa main et .. ?
* La fin de l'hiver
* mana de la création : image : la main pleine de mana
* tir à retardement : au début du prochain tour, inflige X dégâts à tous les ennemis. Genre tir de barrage (flèches)
* S'en remettre au destin : 0 : augmente ou diminue le coût de chaque carte dans ta main de 1 aléatoirement
* carte qui a un effet si jouée pour 1, un effet amélioré si jouée pour 2 et 3.



---
## Pour le personnage
* Compteur du nbr de **fois** que le perso a subi des dégâts pendant ce tour. Compteur s'emballe quand il monte à 5 (genre effet flamme brawl stars) --> cartes avec effets boostés si t as déjà subis 1/3/5 dégâts pendant ce tour. Cartes avec effet : inflige 1 dégât à ton perso, 4 à un ennemi aléatoire et te rend 1 PP (amelioré : et pioche 1 carte?) si t as déjà subi 1 dégât ce tour.
* Relique du perso (se reset à chaque étage) : après chaque combat, possibilité de soit enlever une carte du deck, soit se heal de 20 mais chacun n'est utilisable qu'une fois. Quand c'est épuisé, ça se recharge à l'étage suivant. Ou alors : 
à la fin du combat : donne 4PO si la vie est >20%. Si elle est inférieure à 20% : se heal de 20 puis perd le pouvoir qui se reset à l'étage suivant. Vanité
* Compteur du nombre de dégâts subis pendant la game ! (pas le tour), mot clé Furie : atteint si on a subi 10 dégâts sois-même pendant la game. Mot clé coloré pour les cartes. Effet visuel différent : genre coloré en doré quand on atteint l'état furie --> Les effets des cartes s'activent.
* idée : remplacer le logo Megacrit en y ajoutant des taches de sang. link : https://github.com/QueenMaddii/PrideTheSpire/commit/9183d6151dea722ae52b84468ca85d15e58ddda3
* mot clé : Rampage(1) : si tu as subi 1X des dégâts pendant ce tour.

## Reliques
* améliore toutes les cartes du choix si PV < 35 %
* toutes les 20 cartes piochées, pioche 2 cartes de plus
* relique de boss...? Genre gagne 1 d'énergie 1 tour sur deux, gagne 1 d'énergie au début du tour si chiffre des dizaine des PV pair. (fun en général et possibilité de contrôler ce chiffre si on joue en mode sang)
