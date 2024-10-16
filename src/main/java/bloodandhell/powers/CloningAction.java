package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.utility.SFXAction;

public class CloningAction extends AbstractGameAction {

    private int copyAmount;

    public CloningAction(int amount) {
        this.copyAmount = amount;
        this.duration = 0.0F; // Durée immédiate
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        // Vérifie s'il y a une carte jouée avant celle-ci
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() < 2) {
            // Si aucune carte n'a été jouée avant, ne fais rien et termine l'action
            this.isDone = true;
            return;
        }

        // Récupère la dernière carte jouée (carte avant celle-ci)
        AbstractCard lastCard = AbstractDungeon.actionManager.cardsPlayedThisCombat
                .get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2);

        // Vérifie que la dernière carte n'est pas nulle
        if (lastCard != null) {
            // Crée une copie et l'ajoute à la main
            for (int i = 0; i < this.copyAmount; i++) {
                AbstractCard copy = lastCard.makeStatEquivalentCopy();
                addToBot(new MakeTempCardInHandAction(copy, 1));
            }
            // Optionnel : ajoute un effet sonore ou visuel
            addToTop(new SFXAction("CARD_OBTAIN"));
        }

        this.isDone = true;
    }
}
