package level;

public class Level {
        public static String levelName = "iL";
        public static int levelPartNumber = 0;



        public static LevelPart[] getLevelParts()
        {
                return LevelMap.levelMap.get(levelName);
        }

        public static LevelPart getLevelPart()
        {
                return getLevelParts()[levelPartNumber];
        }

        public static boolean isPartiallySolved(String levelName)
        {
                LevelPart[] levelParts = LevelMap.levelMap.get(levelName);
                if(levelParts != null) {
                        int levelPartsSolved = 0;


                        for (LevelPart levelPart : levelParts) {
                                if (levelPart.isSolved()) {
                                        levelPartsSolved++;
                                }
                        }

                        if(levelPartsSolved > 0 /*(.65 * levelParts.length)*/)
                        {
                                return true;
                        }
                        else
                        {
                                return  false;
                        }
                }
                else
                {
                        return  false;
                }
        }

        public static boolean isTwoThirdsSolved(String levelName)
        {
                LevelPart[] levelParts = LevelMap.levelMap.get(levelName);
                if(levelParts != null) {
                        int levelPartsSolved = 0;


                        for (LevelPart levelPart : levelParts) {
                                if (levelPart.isSolved()) {
                                        levelPartsSolved++;
                                }
                        }

                        if(levelPartsSolved > (.65 * levelParts.length))
                        {
                                return true;
                        }
                        else
                        {
                                return  false;
                        }
                }
                else
                {
                        return  false;
                }
        }

        public static boolean isLevelSolved(String levelName)
        {
                LevelPart[] levelParts = LevelMap.levelMap.get(levelName);
                if(levelParts != null) {
                        for (LevelPart levelPart : levelParts) {
                                if (!levelPart.isSolved()) {
                                        return false;
                                }
                        }
                }
                else
                {
                        return  false;
                }

                return true;
        }

        public static boolean isLevelUnlocked(String levelName)
        {
                if(levelName.equals("tutorial"))
                {
                        return true;
                }
                if(levelName.equals("dL1") || levelName.equals("dT1") || levelName.equals("dS1") || levelName.equals("dC1")
                        || levelName.equals("iL") || levelName.equals("iT") || levelName.equals("iS") || levelName.equals("iC"))
                {
                        if(isTwoThirdsSolved("tutorial"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("dL2"))
                {
                        if(isTwoThirdsSolved("dL1"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("dL3"))
                {
                        if(isTwoThirdsSolved("dL2"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("dC2"))
                {
                        if(isTwoThirdsSolved("dC1"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("sL"))
                {
                        if(isTwoThirdsSolved("iL"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("sT"))
                {
                        if(isTwoThirdsSolved("iT"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("sS"))
                {
                        if(isTwoThirdsSolved("iS"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("sC"))
                {
                        if(isTwoThirdsSolved("iC"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("tL") || levelName.equals("tT") || levelName.equals("tS") || levelName.equals("tC"))
                {
                        if(isTwoThirdsSolved("dL3") || isTwoThirdsSolved("dT1") || isTwoThirdsSolved("dS1") || isTwoThirdsSolved("dC2"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("qL"))
                {
                        if(isTwoThirdsSolved("tL"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("qT"))
                {
                        if(isTwoThirdsSolved("tT"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("qS"))
                {
                        if(isTwoThirdsSolved("tS"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("qC"))
                {
                        if(isTwoThirdsSolved("tC"))
                        {
                                return true;
                        }
                }


                if(levelName.equals("oeL") || levelName.equals("oeT") || levelName.equals("oeS") || levelName.equals("oeC"))
                {
                        if(isTwoThirdsSolved("sL") || isTwoThirdsSolved("sT") || isTwoThirdsSolved("sS") || isTwoThirdsSolved("sC"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ieL"))
                {
                        if(isTwoThirdsSolved("oeL"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ieT"))
                {
                        if(isTwoThirdsSolved("oeT"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ieS"))
                {
                        if(isTwoThirdsSolved("oeS"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ieC"))
                {
                        if(isTwoThirdsSolved("oeC"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ciL"))
                {
                        if(isTwoThirdsSolved("ieL"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ciT"))
                {
                        if(isTwoThirdsSolved("ieT"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ciS"))
                {
                        if(isTwoThirdsSolved("ieS"))
                        {
                                return true;
                        }
                }
                if(levelName.equals("ciC"))
                {
                        if(isTwoThirdsSolved("ieC"))
                        {
                                return true;
                        }
                }


                if(levelName.equals("ci,ieC1") || levelName.equals("ci,ieC2") || levelName.equals("ci,ieC3") || levelName.equals("ci,ieC4"))
                {
                        if((isTwoThirdsSolved("qL") || isTwoThirdsSolved("qT") || isTwoThirdsSolved("qS") || isTwoThirdsSolved("qC")) && (isTwoThirdsSolved("ciL") || isTwoThirdsSolved("ciT") || isTwoThirdsSolved("ciS") || isTwoThirdsSolved("ciC")))
                        {
                                return true;
                        }
                }

                if(levelName.equals("sh,ci,tC1") || levelName.equals("sh,ci,tC2") || levelName.equals("sh,ci,tC3") || levelName.equals("sh,ci,tC4"))
                {
                        if(isTwoThirdsSolved("ci,ieC1") || isTwoThirdsSolved("ci,ieC2") || isTwoThirdsSolved("ci,ieC3") || isTwoThirdsSolved("ci,ieC4"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ci,tC1"))
                {
                        if(isTwoThirdsSolved("sh,ci,tC1"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ci,tC2"))
                {
                        if(isTwoThirdsSolved("sh,ci,tC2"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ci,tC3"))
                {
                        if(isTwoThirdsSolved("sh,ci,tC3"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("ci,tC4"))
                {
                        if(isTwoThirdsSolved("sh,ci,tC4"))
                        {
                                return true;
                        }
                }

                if(levelName.equals("the end"))
                {
                        if(isTwoThirdsSolved("ci,tC1") || isTwoThirdsSolved("ci,tC2") || isTwoThirdsSolved("ci,tC3") || isTwoThirdsSolved("ci,tC4"))
                        {
                                return true;
                        }
                }

                return false;
        }
}
