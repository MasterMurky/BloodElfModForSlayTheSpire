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
            AbstractCard topCard = AbstractDungeon.player.drawPile.getTopCard(); // Récupère la carte du dessus du deck
            // Vérifie si la carte EmergencyAssault est améliorée et si la carte du dessus n'est pas déjà améliorée
            if (this.upgraded && topCard.canUpgrade() && !topCard.upgraded) {
                topCard.upgrade();  // Améliore la carte du dessus si elle peut être améliorée et n'est pas déjà améliorée
            }
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
