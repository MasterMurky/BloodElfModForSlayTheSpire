package bloodandhell.cards.Powers;

import basemod.helpers.TooltipInfo;
import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.powers.CommandingPresencePower;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class CommandingPresence extends BaseCard {
    public static final String ID = makeID(CommandingPresence.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static final int MN = 1;
    private static final int UPG_MN = 1;

    private ArrayList<TooltipInfo> tips;

    public CommandingPresence() {
        super(ID, info);
        setMagic(MN,UPG_MN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p,
                new CommandingPresencePower(p, this.magicNumber), this.magicNumber));
    }


    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (tips == null) {
            tips = new ArrayList<>();
            tips.add(new TooltipInfo("Soldier Strike", "Deal 5 damage. Exhaust"));
            tips.add(new TooltipInfo("Soldier Defend", "Gain 3 Block. Exhaust"));
            tips.add(new TooltipInfo("Soldier Devotion", "Draw 1 card. Exhaust"));
        }
        return tips;
    }
}
