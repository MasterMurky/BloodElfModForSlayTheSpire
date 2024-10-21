package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class DarkPast extends BaseCard {
    public static final String ID = makeID(DarkPast.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int MN = 3;
    private static final int UPG_MN = 1;

    public DarkPast() {
        super(ID, info);
        setMagic(MN, UPG_MN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Donner de la Force (pour booster les dégâts immédiatement)
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));

        // Ne pas appliquer LoseStrengthPower immédiatement après !
        // On attend que toutes les attaques soient terminées avant d'enlever la Force temporaire.

        // Puis à la fin du tour, retirer la Force
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DarkPast();
    }
}
