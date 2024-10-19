package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Replenishment extends BaseCard {
    public static final String ID = makeID(Replenishment.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR, // La couleur de la carte, spécifique à ton personnage
            CardType.SKILL, // Type de la carte (Attaque, Compétence, Pouvoir)
            CardRarity.UNCOMMON, // Rareté (Commun, Peu commun, Rare)
            CardTarget.SELF, // La cible (soi-même ici)
            1 // Coût de la carte
    );

    public Replenishment() {
        super(ID, info);
        this.magicNumber = this.baseMagicNumber = 2; // Définit la quantité de cartes piochées (2 cartes)
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Pioche 2 cartes
        addToBot(new DrawCardAction(this.magicNumber, new HandCheckAction() {
            @Override
            public void update() {
                // Rafraîchit la main après la pioche
                AbstractDungeon.player.hand.refreshHandLayout();

                // Si la carte n'est pas améliorée, on ne réduit le coût que d'une seule carte "Defend"
                if (!upgraded) {
                    for (AbstractCard card : p.hand.group) {
                        if (card.name.contains("Defend") && !card.freeToPlayOnce) {
                            card.freeToPlayOnce = true; // Réduit le coût à 0 jusqu'à ce qu'elle soit jouée
                            break;
                        }
                    }
                } else {
                    // Si la carte est améliorée, on réduit le coût de toutes les cartes "Defend"
                    for (AbstractCard card : p.hand.group) {
                        if (card.name.contains("Defend") && !card.freeToPlayOnce) {
                            card.freeToPlayOnce = true;
                        }
                    }
                }

                this.isDone = true;
            }
        }));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Replenishment();
    }
}
