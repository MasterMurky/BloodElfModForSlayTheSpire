package bloodandhell.relics;

import bloodandhell.character.MyCharacter;
import bloodandhell.potions.LiquidVitality;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static bloodandhell.BasicMod.makeID;

public class banner extends BaseRelic {
    private static final String NAME = "banner"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    // STARTER, COMMON, UNCOMMON, RARE, SHOP, BOSS, SPECIAL (for events)
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    // Les cartes de récompense n'existent pas encore au moment d'onVictory() : elles sont
    // créées plus tard par CombatRewardScreen.setupItemReward(). On pose donc juste un
    // drapeau ici, et bloodandhell.patches.BannerCardUpgradePatch fait l'amélioration
    // juste après que les cartes aient été générées.
    private boolean pendingCardUpgrade = false;

    public boolean consumePendingCardUpgrade() {
        boolean pending = pendingCardUpgrade;
        pendingCardUpgrade = false;
        return pending;
    }

    // Two constructors : one for character's relics and one for basic relics
    // Don't write the color parameter if it isn't a relic for a character : super(ID, NAME, RARITY, SOUND);

    // Base game hooks :
    // decompile AbstractCard

    // Special hooks :
    // https://github.com/daviscook477/BaseMod/wiki/Hooks
    // https://github.com/kiooeht/StSLib/wiki/Relic-Hooks
    public banner() {
        super(ID, NAME, MyCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onVictory() {
        flash();
        int tensDigit = (AbstractDungeon.player.currentHealth / 10) % 10;
        if (tensDigit % 2 == 0) {
            pendingCardUpgrade = true;
        } else {
            AbstractDungeon.getCurrRoom().addPotionToRewards(new LiquidVitality());
        }
    }

    // Automatically updates the description in the json file
    @Override
    public String getUpdatedDescription() {
        if (DESCRIPTIONS == null || DESCRIPTIONS.length == 0) {
            return "At the end of combat, if your HP digit in the tens place is even, one of your card rewards is upgraded. If it's odd, Gain a potion that raises your max HP by 2 and heals 1 HP when consumed.";
        }
        return DESCRIPTIONS[0];
    }
}