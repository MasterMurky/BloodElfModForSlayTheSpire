package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.concurrent.ThreadLocalRandom;

public class LeaveItToFatePower extends AbstractGameAction {
    private int threshold;

    public LeaveItToFatePower(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 11);

            // Si randomNumber <= threshold, on diminue le coût pour ce tour
            if (randomNumber <= threshold) {
                c.setCostForTurn(c.costForTurn - 1);  // Réduit le coût pour ce tour (affichage en vert)
            } else {
                c.setCostForTurn(c.costForTurn + 1);  // Augmente le coût pour ce tour (affichage en rouge)
            }
        }
        this.isDone = true;
    }
}
