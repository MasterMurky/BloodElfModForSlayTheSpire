package bloodandhell.cards.Skills;

import basemod.helpers.TooltipInfo;
import bloodandhell.cards.BaseCard;
import bloodandhell.cards.tempCards.SoldierDefend;
import bloodandhell.cards.tempCards.SoldierDevotion;
import bloodandhell.cards.tempCards.SoldierStrike;
import bloodandhell.cards.tempCards.NotASoldier;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class ArmyOfSoldiers extends BaseCard {
    public static final String ID = makeID(ArmyOfSoldiers.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    private static final int CARDS_TO_ADD = 3;
    private static final int UPG_CARDS_TO_ADD = 2;

    private ArrayList<TooltipInfo> tips;

    public ArmyOfSoldiers() {
        super(ID, info);
        setMagic(CARDS_TO_ADD, UPG_CARDS_TO_ADD);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            AbstractCard card;
            int roll = AbstractDungeon.cardRandomRng.random(49); //  0 a 49

            if (roll == 0) {
                card = new NotASoldier(); //
            } else {
                ArrayList<AbstractCard> pool = new ArrayList<>();
                pool.add(new SoldierStrike());
                pool.add(new SoldierDefend());
                pool.add(new SoldierDevotion());

                card = pool.get(AbstractDungeon.cardRandomRng.random(pool.size() - 1)).makeCopy();
            }

            addToBot(new MakeTempCardInHandAction(card, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ArmyOfSoldiers();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        if (tips == null) {
            tips = new ArrayList<>();
            addTip(tips, SoldierStrike.ID);
            addTip(tips, SoldierDefend.ID);
            addTip(tips, SoldierDevotion.ID);
        }
        return tips;
    }

    private static void addTip(List<TooltipInfo> tips, String cardID) {
        // Ces 3 tooltips étaient codées en dur en anglais alors que CardStrings.json a déjà la
        // traduction FR (SoldierStrike/SoldierDefend/SoldierDevotion) -- on la réutilise ici.
        CardStrings strings = CardCrawlGame.languagePack.getCardStrings(cardID);
        tips.add(new TooltipInfo(strings.NAME, strings.DESCRIPTION));
    }
}
