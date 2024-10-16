package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;

public class Assault extends BaseCard {
    public static final String ID = makeID(Assault.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    public Assault() {
        super(ID, info);
    }

    public Assault(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard topCard = AbstractDungeon.player.drawPile.getTopCard(); // Récupère la carte du dessus de la pile

        if (topCard != null) {
            // Si Assault est améliorée et la carte du dessus peut être améliorée
            if (this.upgraded && topCard.canUpgrade() && !topCard.upgraded) {
                addToBot(new UpgradeAndPlayCardAction(topCard)); // Ajoute une action qui améliore puis joue la carte
            } else {
                // Si pas besoin d'améliorer, on joue directement
                addToBot(new PlayTopCardAction(
                        AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false
                ));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Assault();
    }

    // Action pour améliorer la carte avant de la jouer
    private static class UpgradeAndPlayCardAction extends AbstractGameAction {
        private AbstractCard cardToPlay;

        public UpgradeAndPlayCardAction(AbstractCard cardToPlay) {
            this.cardToPlay = cardToPlay;
            this.actionType = ActionType.CARD_MANIPULATION;
        }

        @Override
        public void update() {
            // Améliore la carte
            if (cardToPlay.canUpgrade() && !cardToPlay.upgraded) {
                cardToPlay.upgrade();
            }

            // Force la mise à jour des pouvoirs pour recalculer les dégâts ou le bloc
            cardToPlay.applyPowers();

            // Puis joue la carte
            addToTop(new PlayTopCardAction(
                    AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false
            ));

            this.isDone = true;  // Marque l'action comme terminée
        }
    }
}
