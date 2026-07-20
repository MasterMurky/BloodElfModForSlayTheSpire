package bloodandhell.cards.Skills;

import bloodandhell.cards.BaseCard;
import bloodandhell.character.MyCharacter;
import bloodandhell.util.CardStats;
import bloodandhell.util.DeferredCheckAction;
import bloodandhell.util.SelfDamageTracker;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodyArmor extends BaseCard {
    public static final String ID = makeID(BloodyArmor.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MyCharacter.Enums.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );

    private static final int BASE_HITS = 1;
    private static final int UPG_BASE_HITS = 2;
    private static final int THRESHOLD = 2;
    private static final int UPG_THRESHOLD = 3;

    private int baseHits = BASE_HITS;
    private int threshold = THRESHOLD;

    public BloodyArmor() {
        super(ID, info);
    }

    public BloodyArmor(String ID, CardStats info) {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Les baseHits (1 de base, 2 amélioré) ont TOUJOURS lieu en premier -- c'est justement
        // les dégâts qu'ils infligent qui doivent pouvoir déclencher le seuil de Saignée
        // ci-dessous. Le check est donc différé (DeferredCheckAction) pour s'exécuter après que
        // ces LoseHPAction aient réellement résolu et incrémenté SelfDamageTracker.hitsThisTurn.
        for (int i = 0; i < this.baseHits; i++) {
            addToBot(new LoseHPAction(p, p, 1));
            addToBot(new GainBlockAction(p, p, 5));
        }

        final int checkThreshold = this.threshold;
        addToBot(new DeferredCheckAction(() -> {
            if (SelfDamageTracker.hasRampage(checkThreshold)) {
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, 1));
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, 5));
            }
        }));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.baseHits = UPG_BASE_HITS;
            this.threshold = UPG_THRESHOLD;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    //Optional
    @Override
    public AbstractCard makeCopy() {
        return new BloodyArmor();
    }
}
