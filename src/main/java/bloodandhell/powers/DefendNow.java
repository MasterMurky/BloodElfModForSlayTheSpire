package bloodandhell.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefendNow extends AbstractCard {
    public static final String ID = "DefendNow";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("DefendNow");

    public DefendNow() {
        super("DefendNow", cardStrings.NAME, "colorless/power/defend_now", -2, cardStrings.DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GainBlockAction(p, p, this.magicNumber));
            }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);

        }
    }

    public AbstractCard makeCopy() {
        return new DefendNow();
    }
}
