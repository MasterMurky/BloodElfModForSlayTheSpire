package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EmergencyAssault extends BaseCard {
    public static final String ID = makeID(EmergencyAssault.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3
    );

    public EmergencyAssault() {
        super(ID, info);
    }

    public EmergencyAssault(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            // Les 3 PlayTopCardAction sont mises en file et ne s'exécutent pas immédiatement :
            // vérifier/améliorer la carte du dessus ici, avant qu'aucune n'ait encore été jouée,
            // reviendrait à regarder 3 fois la même carte. On met donc la vérification elle-même
            // en file, pour qu'elle s'exécute juste avant chaque PlayTopCardAction correspondante,
            // une fois les cartes précédentes réellement retirées du drawPile.
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractCard topCard = AbstractDungeon.player.drawPile.getTopCard();
                    if (topCard != null && upgraded && topCard.canUpgrade() && !topCard.upgraded) {
                        topCard.upgrade();
                    }
                    this.isDone = true;
                }
            });
            addToBot(new PlayTopCardAction(
                    AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false
            ));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new EmergencyAssault();
    }
}
