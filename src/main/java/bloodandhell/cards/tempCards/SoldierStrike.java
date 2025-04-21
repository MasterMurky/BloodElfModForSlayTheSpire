package bloodandhell.cards.tempCards;

import basemod.AutoAdd; // to use ignore AutoAdd

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@AutoAdd.Ignore //  AutoAdd is ignoring the card
public class SoldierStrike extends BaseCard {
    public static final String ID = makeID(SoldierStrike.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0
    );

    private static final int DMG = 5;
    private static final int UPG_DMG = 3;

    public SoldierStrike() {
        super(ID, info);
        setDamage(DMG, UPG_DMG);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SoldierStrike();
    }
}
