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
        // getTopCard() plante (IndexOutOfBoundsException) si la pioche est vide -- on ne peut donc
        // le lire que si elle contient encore au moins une carte. Si la pioche est vide,
        // PlayTopCardAction gère déjà tout seul le rebrassage de la défausse (ou l'absence totale
        // de carte à jouer) ; on ne peut simplement pas savoir/améliorer la carte à l'avance.
        if (!AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractCard topCard = AbstractDungeon.player.drawPile.getTopCard();
            if (this.upgraded && topCard.canUpgrade() && !topCard.upgraded) {
                addToBot(new UpgradeAndPlayCardAction(topCard)); // Ajoute une action qui améliore puis joue la carte
                return;
            }
        }
        // Si pas besoin d'améliorer (ou pioche vide), on joue directement
        addToBot(new PlayTopCardAction(
                AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false
        ));
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
