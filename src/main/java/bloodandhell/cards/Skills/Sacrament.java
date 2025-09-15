package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import bloodandhell.powers.SacramentGoldPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sacrament extends BaseCard {
    public static final String ID = makeID(Sacrament.class.getSimpleName());

    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    private static final int GOLD_GAIN = 10;
    private static final int UPG_GOLD_GAIN = 5;
    private static final int GOLD_PER_KILL = 5;

    public Sacrament() {
        super(ID, info);
        setMagic(GOLD_GAIN, UPG_GOLD_GAIN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Gain immédiat
        addToBot(new GainGoldAction(this.magicNumber));

        // Ajoute le pouvoir qui gère le gain par kill
        addToBot(new ApplyPowerAction(p, p, new SacramentGoldPower(p, GOLD_PER_KILL)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sacrament();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_GOLD_GAIN); // 10 → 15
        }
    }
}
