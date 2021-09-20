package scripts.scripter_app.agility.data;


import org.tribot.api.General;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.scripter_app.api.SettingRequirement;

public class RooftopAgilityData {

	static SettingRequirement priestInPerilQuestReq = new SettingRequirement(302, 61, false);

	public enum RooftopObstacles {

//		ARDOUGNE("Ardougne Rooftop", 90, ARDOUGNE_COURSE, ARDOUGNE_START_TILE),
		RELLEKKA("Rellekka Rooftop", 80, RELLEKKA_COURSE, RELLEKKA_START_TILE),
		POLLNIVNEACH("Pollnivneach Rooftop", 70, POLLNIVEACH_COURSE, POLLNIVEACH_START_TILE),
		SEERS("Seers' Village Rooftop", 60, SEERS_COURSE, SEERS_START_TILE),
		FALADOR("Falador Rooftop", 50, FALADOR_COURSE, FALADOR_START_TILE),
		CANIFIS("Canifis Rooftop", 40, CANIFIS_COURSE, CANIFIS_START_TILE),
		VARROCK("Varrock Rooftop", 30, VARROCK_COURSE, VARROCK_START_TILE),
		AL_KHARID("Al Kharid Rooftop", 20, AL_KHARID_COURSE, AL_KHARID_START_TILE),
		DRAYNOR("Draynor Village Rooftop", 10, DRAYNOR_COURSE, DRAYNOR_START_TILE),
		GNOME("Gnome", 1, GNOME_COURSE, GNOME_START_TILE);

		private String courseName;
		private int levelReq;
		private RooftopObstacle[] course;
		private RSTile startTile;

		RooftopObstacles(String courseName, int levelReq, RooftopObstacle[] course, RSTile startTile) {
			this.courseName = courseName;
			this.levelReq = levelReq;
			this.course = course;
			this.startTile = startTile;
		}

		public String getCourseName() {
			return this.courseName;
		}

		public int getLevelReq() {
			return this.levelReq;
		}

		public RooftopObstacle[] getCourse() {
			return this.course;
		}

		public RSTile getStartTile() {
			return this.startTile;
		}
		

	}
	
	public static RooftopObstacles getBestObstacle() {
		int agilityLevel = Skills.getActualLevel(SKILLS.AGILITY);
		for (RooftopObstacles obstacle : RooftopObstacles.values()) {
			String obstacleName = obstacle.getCourseName();
			if (obstacleName.equalsIgnoreCase("Canifis Rooftop") && !priestInPerilQuestReq.isQuestRequirementValid()) {
				General.println("[Agility - Data] Priest In Peril Not Complete, Skipping Canifis");
				continue;
			}
			int reqLevel = obstacle.getLevelReq();
			if (reqLevel <= agilityLevel) {
				General.println("[Agility - Data] Best Course: " + obstacleName);
				return obstacle;
			}
		}
		return RooftopObstacles.GNOME;
	}
	
	public static RSTile AL_KHARID_START_TILE = new RSTile(3273, 3195, 0),
			ARDOUGNE_START_TILE = new RSTile(2673, 3297, 0),
			CANIFIS_START_TILE = new RSTile(3507, 3487, 0),
			DRAYNOR_START_TILE = new RSTile(3103, 3279, 0),
			FALADOR_START_TILE = new RSTile(3035, 3340, 0),
			GNOME_START_TILE = new RSTile(2474, 3437, 0),
			POLLNIVEACH_START_TILE = new RSTile(3352, 2963, 0),
			RELLEKKA_START_TILE = new RSTile(2625, 3678, 0),
			SEERS_START_TILE = new RSTile(2729, 3487, 0),
			VARROCK_START_TILE = new RSTile(3222, 3415, 0);
	
	public static RooftopObstacle[] AL_KHARID_COURSE = {
			
			new RooftopObstacle("Rough wall" , new RSTile(3273, 3195, 0), new RSArea(new RSTile(3277, 3195, 0), new RSTile(3270, 3200, 0)), new DeltaPositionHover(-1, 10)),
			new RooftopObstacle("Tightrope" , new RSTile(3272, 3181, 3), new RSArea(new RSTile(3271, 3192, 3), new RSTile(3278, 3180, 3)), new DeltaPositionHover(-3, -6)),
			new RooftopObstacle("Cable" , new RSTile(3269, 3166, 3), new RSArea(new RSTile(3265, 3173, 3), new RSTile(3272, 3161, 3))), //too far
			new RooftopObstacle("Zip line" , new RSTile(3302, 3163, 3), new RSArea(new RSTile(3283, 3176, 3), new RSTile(3302, 3160, 3)), new DeltaPositionHover(3, 3)),
			new RooftopObstacle("Tropical tree" , new RSTile(3318, 3166, 1), new RSArea(new RSTile(3313, 3165, 1), new RSTile(3318, 3160, 1)), new DeltaPositionHover(-1, 5)),
			new RooftopObstacle("Roof top beams" , new RSTile(3316, 3179, 2), new RSArea(new RSTile(3317, 3174, 2), new RSTile(3313, 3179, 2)), new DeltaPositionHover(-3, 6)),
			new RooftopObstacle("Tightrope" , new RSTile(3313, 3186, 3), new RSArea(new RSTile(3318, 3180, 3), new RSTile(3312, 3186, 3)), new DeltaPositionHover(-2, 6)),
			new RooftopObstacle("Gap" , new RSTile(3300, 3193, 3), new RSArea(new RSTile[] {new RSTile(3301, 3196, 3), new RSTile(3308, 3189, 3), new RSTile(3302, 3184, 3),
					new RSTile(3296, 3191, 3)})), // minimap
			new RooftopObstacle("Rough wall" , new RSTile(3273, 3195, 0), new RSArea(new RSTile[] {new RSTile(3301, 3195, 0), new RSTile(3298, 3198, 0), new RSTile(3293, 3194, 0),
					new RSTile(3297, 3191, 0)}), new DeltaPositionHover(-1, -10)),
			
	};
	
	public static RooftopObstacle[] ARDOUGNE_COURSE = {
			
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			new RooftopObstacle("", new RSTile(0,0,0), new RSArea(new RSTile(0,0,0), new RSTile(0,0,0))),
			
	};
	
	public static RooftopObstacle[] CANIFIS_COURSE = {
			
			new RooftopObstacle("Tall tree" , new RSTile(3505, 3489, 0), new RSArea(new RSTile(3499, 3490, 0), new RSTile(3510, 3480, 0)), new DeltaPositionHover(-1, 6)),
			new RooftopObstacle("Gap" , new RSTile(3505, 3498, 2), new RSArea(new RSTile(3505, 3489, 2), new RSTile(3511, 3498, 2)), new DeltaPositionHover(-6, 0)),
			new RooftopObstacle("Gap" , new RSTile(3496, 3504, 2), new RSArea(new RSTile(3503, 3504, 2), new RSTile(3497, 3506, 2)), new DeltaPositionHover(-7, -5)),
			new RooftopObstacle("Gap" , new RSTile(3485, 3499, 2), new RSArea(new RSTile(3492, 3504, 2), new RSTile(3485, 3499, 2)), new DeltaPositionHover(-1, -8)),
			new RooftopObstacle("Gap" , new RSTile(3478, 3491, 3), new RSArea(new RSTile(3479, 3499, 3), new RSTile(3475, 3492, 3)), new DeltaPositionHover(2, -3)),
			new RooftopObstacle("Pole-vault" , new RSTile(3480, 3483, 2), new RSArea(new RSTile(3484, 3487, 2), new RSTile(3477, 3481, 2)), new DeltaPositionHover(14, 0)),
			new RooftopObstacle("Gap" , new RSTile(3503, 3476, 3), new RSArea(new RSTile(3486, 3478, 3), new RSTile(3505, 3469, 3)), new DeltaPositionHover(0, 7)),
			new RooftopObstacle("Gap" , new RSTile(3510, 3483, 2), new RSArea(new RSTile(3515, 3482, 2), new RSTile(3508, 3475, 2)), new DeltaPositionHover(-5, 4)),
			new RooftopObstacle("Tall tree" , new RSTile(3505, 3489, 0), new RSArea(new RSTile(3514, 3485, 0), new RSTile(3502, 3491, 0)), new DeltaPositionHover(-1, 6)),
			
	};
	
	public static RooftopObstacle[] DRAYNOR_COURSE = {
			
			new RooftopObstacle("Rough wall" , new RSTile(3103, 3279, 0), new RSArea(new RSTile(3103, 3274, 0), new RSTile(3106, 3281, 0)), new DeltaPositionHover(-4, -2)),
			new RooftopObstacle("Tightrope" , new RSTile(3098, 3277, 3), new RSArea(new RSTile(3102, 3281, 3), new RSTile(3099, 3277, 3)), new DeltaPositionHover(2, 0)),
			new RooftopObstacle("Tightrope" , new RSTile(3092, 3276, 3), new RSArea(new RSTile(3086, 3274, 3), new RSTile(3093, 3276, 3)), new DeltaPositionHover(-3, -2)),
			new RooftopObstacle("Narrow wall" , new RSTile(3089, 3264, 3), new RSArea(new RSTile(3089, 3265, 3), new RSTile(3094, 3267, 3)), new DeltaPositionHover(0, -5)),
			new RooftopObstacle("Wall" , new RSTile(3088, 3256, 3), new RSArea(new RSTile(3088, 3261, 3), new RSTile(3088, 3257, 3)), new DeltaPositionHover(7, 0)),
			new RooftopObstacle("Gap" , new RSTile(3095, 3255, 3), new RSArea(new RSTile(3087, 3255, 3), new RSTile(3094, 3255, 3)), new DeltaPositionHover(6, 5)),
			new RooftopObstacle("Crate" , new RSTile(3102, 3261, 3), new RSArea(new RSTile(3096, 3256, 3), new RSTile(3101, 3261, 3))), // minimap
			new RooftopObstacle("Rough wall" , new RSTile(3103, 3279, 0), new RSArea(new RSTile(3102, 3257, 0), new RSTile(3106, 3264, 0)), new DeltaPositionHover(-4, -2)),
			
	};
	
	public static RooftopObstacle[] FALADOR_COURSE = {
			
			new RooftopObstacle("Rough wall" , new RSTile(3036, 3341, 0), new RSArea(new RSTile(3032, 3341, 0), new RSTile(3043, 3335, 0)), new DeltaPositionHover(4, 1)),
			new RooftopObstacle("Tightrope" , new RSTile(3040, 3343, 3), new RSArea(new RSTile(3036, 3343, 3), new RSTile(3040, 3342, 3)), new DeltaPositionHover(3, 6)),
			new RooftopObstacle("Hand holds" , new RSTile(3050, 3350, 3), new RSArea(new RSTile(3045, 3340, 3), new RSTile(3051, 3349, 3)), new DeltaPositionHover(-2, 2)),
			new RooftopObstacle("Gap" , new RSTile(3048, 3359, 3), new RSArea(new RSTile(3050, 3357, 3), new RSTile(3048, 3358, 3)), new DeltaPositionHover(-4, 0)),
			new RooftopObstacle("Gap" , new RSTile(3044, 3361, 3), new RSArea(new RSTile(3048, 3361, 3), new RSTile(3045, 3367, 3)), new DeltaPositionHover(-7, 0)),
			new RooftopObstacle("Tightrope" , new RSTile(3034, 3361, 3), new RSArea(new RSTile(3041, 3364, 3), new RSTile(3034, 3361, 3)), new DeltaPositionHover(-2, -1)),
			new RooftopObstacle("Tightrope" , new RSTile(3026, 3353, 3), new RSArea(new RSTile(3029, 3352, 3), new RSTile(3026, 3354, 3)), new DeltaPositionHover(-4, -1)),
			new RooftopObstacle("Gap" , new RSTile(3016, 3352, 3), new RSArea(new RSTile(3021, 3358, 3), new RSTile(3009, 3353, 3)), new DeltaPositionHover(-3, -4)),
			new RooftopObstacle("Ledge" , new RSTile(3015, 3345, 3), new RSArea(new RSTile(3022, 3343, 3), new RSTile(3016, 3349, 3)), new DeltaPositionHover(-3, -3)),
			new RooftopObstacle("Ledge" , new RSTile(3011, 3343, 3), new RSArea(new RSTile(3014, 3346, 3), new RSTile(3011, 3344, 3)), new DeltaPositionHover(1, -7)),
			new RooftopObstacle("Ledge" , new RSTile(3014, 3335, 3), new RSArea(new RSTile(3009, 3342, 3), new RSTile(3013, 3335, 3)), new DeltaPositionHover(3, -2)),
			new RooftopObstacle("Ledge" , new RSTile(3018, 3332, 3), new RSArea(new RSTile(3017, 3334, 3), new RSTile(3012, 3331, 3)), new DeltaPositionHover(6, -2)),
			new RooftopObstacle("Edge" , new RSTile(3025, 3332, 3), new RSArea(new RSTile(3019, 3332, 3), new RSTile(3024, 3335, 3)), new DeltaPositionHover(7, 7)),
			new RooftopObstacle("Rough wall" , new RSTile(3036, 3341, 0), new RSArea(new RSTile(3028, 3331, 0), new RSTile(3032, 3338, 0)), new DeltaPositionHover(4, 1)),
			
	};
	
	public static RooftopObstacle[] GNOME_COURSE = {
			
			new RooftopObstacle("Log balance" ,new RSTile(2474, 3435, 0), new RSArea(new RSTile(2481, 3435, 0), new RSTile(2469, 3440, 0))),
			new RooftopObstacle("Obstacle net" , new RSTile(2473, 3425, 0), new RSArea(new RSTile(2478, 3426, 0), new RSTile(2470, 3429, 0))),
			new RooftopObstacle("Tree branch" , new RSTile(2473, 3422, 1), new RSArea(new RSTile(2476, 3422, 1), new RSTile(2471, 3424, 1))),
			new RooftopObstacle("Balancing rope" , new RSTile(2478, 3420, 2), new RSArea(new RSTile(2472, 3418, 2), new RSTile(2477, 3421, 2))),
			new RooftopObstacle("Tree branch" , new RSTile(2486, 3419, 2), new RSArea(new RSTile(2488, 3421, 2), new RSTile(2483, 3418, 2))),
			new RooftopObstacle("Obstacle net" , new RSTile(2485, 3426, 0), new RSArea(new RSTile(2489, 3417, 0), new RSTile(2482, 3425, 0))),
			new RooftopObstacle("Obstacle pipe" , new RSTile(2484, 3431, 0), new RSArea(new RSTile(2483, 3431, 0), new RSTile(2488, 3427, 0))),
			new RooftopObstacle("Log balance" ,new RSTile(2474, 3435, 0), new RSArea(new RSTile(2490, 3436, 0), new RSTile(2481, 3440, 0))), // end
			
	};
	
	public static RooftopObstacle[] POLLNIVEACH_COURSE = {
			
			new RooftopObstacle("Basket" , new RSTile(3351, 2962, 0), new RSArea(new RSTile(3347, 2968, 0), new RSTile(3355, 2957, 0)), new DeltaPositionHover(-2, 6)),
			new RooftopObstacle("Market stall" , new RSTile(3349, 2970, 1), new RSArea(new RSTile(3351, 2968, 1), new RSTile(3346, 2962, 1)), new DeltaPositionHover(4, 5)),
			new RooftopObstacle("Banner" , new RSTile(3356, 2978, 1), new RSArea(new RSTile(3352, 2973, 1), new RSTile(3355, 2976, 1)), new DeltaPositionHover(3, -1)),
			new RooftopObstacle("Gap" , new RSTile(3363, 2976, 1), new RSArea(new RSTile(3360, 2977, 1), new RSTile(3362, 2979, 1)), new DeltaPositionHover(1, 1)),
			new RooftopObstacle("Tree" , new RSTile(3367, 2977, 1), new RSArea(new RSTile(3366, 2976, 1), new RSTile(3369, 2974, 1)), new DeltaPositionHover(-3, 0)),
			new RooftopObstacle("Rough wall" , new RSTile(3365, 2982, 1), new RSArea(new RSTile(3365, 2986, 1), new RSTile(3369, 2982, 1)), new DeltaPositionHover(-7, 2)),
			new RooftopObstacle("Monkeybars" , new RSTile(3358, 2985, 2), new RSArea(new RSTile(3365, 2981, 2), new RSTile(3355, 2984, 2)), new DeltaPositionHover(1, 5)),
			new RooftopObstacle("Tree" , new RSTile(3359, 2996, 2), new RSArea(new RSTile(3370, 2990, 2), new RSTile(3357, 2995, 2)), new DeltaPositionHover(4, 0)),
			new RooftopObstacle("Drying line" , new RSTile(3363, 3000, 2), new RSArea(new RSTile(3356, 3000, 2), new RSTile(3362, 3004, 2))), // minimap
			new RooftopObstacle("Basket" , new RSTile(3351, 2962, 0), new RSArea(new RSTile(3360, 2999, 0), new RSTile(3368, 2996, 0)), new DeltaPositionHover(-2, 6)),
			
	};
	
	public static RooftopObstacle[] RELLEKKA_COURSE = {
			
			new RooftopObstacle("Rough wall" , new RSTile(2625, 3677, 0), new RSArea(new RSTile(2631, 3673, 0), new RSTile(2620, 3683, 0)), new DeltaPositionHover(-4, -7)),
			new RooftopObstacle("Gap" , new RSTile(2621, 3669, 3), new RSArea(new RSTile(2626, 3676, 3), new RSTile(2622, 3672, 3)), new DeltaPositionHover(1, -10)),
			new RooftopObstacle("Tightrope" , new RSTile(2623, 3658, 3), new RSArea(new RSTile(2622, 3668, 3), new RSTile(2615, 3658, 3)), new DeltaPositionHover(2, 2)),
			new RooftopObstacle("Gap" , new RSTile(2629, 3656, 3), new RSArea(new RSTile(2626, 3651, 3), new RSTile(2630, 3655, 3)), new DeltaPositionHover(4, 1)),
			new RooftopObstacle("Gap" , new RSTile(2643, 3654, 3), new RSArea(new RSTile(2639, 3653, 3), new RSTile(2644, 3649, 3)), new DeltaPositionHover(4, 6)),
			new RooftopObstacle("Tightrope" , new RSTile(2647, 3663, 3), new RSArea(new RSTile(2650, 3662, 3), new RSTile(2643, 3657, 3)), new DeltaPositionHover(-1, 6)),
			new RooftopObstacle("Pile of fish" , new RSTile(2654, 3676, 3), new RSArea(new RSTile(2655, 3665, 3), new RSTile(2666, 3685, 3))), // minimap
			new RooftopObstacle("Rough wall" , new RSTile(2625, 3677, 0), new RSArea(new RSTile(2654, 3682, 0), new RSTile(2647, 3668, 0)), new DeltaPositionHover(-4, -7)),
	};
	
	public static RooftopObstacle[] SEERS_COURSE = {
			
			new RooftopObstacle("Wall" , new RSTile(2729, 3489, 0), new RSArea(new RSTile(2725, 3491, 0), new RSTile(2733, 3485, 0)), new DeltaPositionHover(-9, 1)),
			new RooftopObstacle("Gap" , new RSTile(2720, 3492, 3), new RSArea(new RSTile(2730, 3490, 3), new RSTile(2721, 3497, 3)), new DeltaPositionHover(-3, -5)),
			new RooftopObstacle("Tightrope" , new RSTile(2710, 3489, 2), new RSArea(new RSTile(2705, 3488, 2), new RSTile(2713, 3495, 2)), new DeltaPositionHover(0, -4)),
			new RooftopObstacle("Gap" , new RSTile(2710, 3476, 2), new RSArea(new RSTile(2715, 3481, 2), new RSTile(2710, 3477, 2)), new DeltaPositionHover(-10, -3)),
			new RooftopObstacle("Gap" , new RSTile(2700, 3469, 3), new RSArea(new RSTile(2715, 3470, 3), new RSTile(2700, 3475, 3)), new DeltaPositionHover(1, -4)),
			new RooftopObstacle("Edge" , new RSTile(2703, 3461, 2), new RSArea(new RSTile(2698, 3460, 2), new RSTile(2702, 3465, 2))), // minimap
			new RooftopObstacle("Wall" , new RSTile(2729, 3489, 0), new RSArea(new RSTile(2704, 3457, 0), new RSTile(2710, 3467, 0)), new DeltaPositionHover(-9, 1)),
	};
	
	public static RooftopObstacle[] VARROCK_COURSE = {
			
			new RooftopObstacle("Rough wall" , new RSTile(3221, 3414, 0), new RSArea(new RSTile(3221, 3411, 0), new RSTile(3225, 3417, 0)), new DeltaPositionHover(-6, 0)),
			new RooftopObstacle("Clothes line" , new RSTile(3213, 3414, 3), new RSArea(new RSTile(3214, 3410, 3), new RSTile(3219, 3419, 3)), new DeltaPositionHover(-8, 2)),
			new RooftopObstacle("Gap" , new RSTile(3200, 3416, 3), new RSArea(new RSTile(3208, 3414, 3), new RSTile(3201, 3420, 3)), new DeltaPositionHover(-6, -1)),
			new RooftopObstacle("Wall" , new RSTile(3191, 3415, 1), new RSArea(new RSTile(3197, 3416, 1), new RSTile(3194, 3416, 1)), new DeltaPositionHover(1, -5)),
			new RooftopObstacle("Gap" , new RSTile(3193, 3401, 3), new RSArea(new RSTile(3192, 3406, 3), new RSTile(3198, 3402, 3))), // too far
			new RooftopObstacle("Gap" , new RSTile(3209, 3397, 3), new RSArea(new RSTile[] { new RSTile(3181, 3382, 3),new RSTile(3190, 3382, 3), // too far
					new RSTile(3190, 3388, 3), new RSTile(3196, 3388, 3), new RSTile(3196, 3390, 3), new RSTile(3201, 3395, 3),
					new RSTile(3209, 3395, 3), new RSTile(3209, 3404, 3), new RSTile(3202, 3404, 3), new RSTile(3202, 3399, 3),
					new RSTile(3181, 3399, 3)})),
			new RooftopObstacle("Gap" , new RSTile(3233, 3402, 3), new RSArea(new RSTile(3213, 3405, 3), new RSTile(3233, 3392, 3)), new DeltaPositionHover(0, 6)),
			new RooftopObstacle("Ledge" , new RSTile(3236, 3409, 3), new RSArea(new RSTile(3236, 3403, 3), new RSTile(3240, 3408, 3)), new DeltaPositionHover(0, 6)),
			new RooftopObstacle("Edge" , new RSTile(3236, 3416, 3), new RSArea(new RSTile(3240, 3410, 3), new RSTile(3236, 3415, 3))),
			new RooftopObstacle("Rough wall" , new RSTile(3221, 3414, 0), new RSArea(new RSTile(3240, 3417, 0), new RSTile(3234, 3419, 0)), new DeltaPositionHover(-6, 0)),
			
	};
	
}
