package level;

import java.util.Map;

public class LevelMap {
    static Map<String, LevelPart[]> levelMap = Map.ofEntries(
            //TUTORIAL LEVELS
            Map.entry("tutorial",new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-1"}),
                    new LevelPart("circle",new String[]{"sh-5"}),
                    new LevelPart("circle",new String[]{"tutorial2-true"}),
                    new LevelPart("circle",new String[]{"tutorial3-true"}),
                    new LevelPart("circle",new String[]{"tutorial4-true"}),
                    new LevelPart("circle",new String[]{"tutorial5-true"}),
            }),

            //BASIC LEVELS
            Map.entry("bL",new LevelPart[] {new LevelPart("line", 1, "b-true")}),
            Map.entry("bT", new LevelPart[] {new LevelPart("triangle", 1, "b-true")}),
            Map.entry("bS", new LevelPart[] {new LevelPart("square", 1, "b-true")}),
            Map.entry("bC", new LevelPart[] {new LevelPart("circle", 1, "b-true")}),

            //DISTINCT LEVELS
            Map.entry("dL1", new LevelPart[] {
                    new LevelPart("line", 2, "d-true"),
                    new LevelPart("line", 2, "d-true")
            }),
            Map.entry("dL2", new LevelPart[]{
                    new LevelPart("line", 3, "d-true"),
                    new LevelPart("line", 3, "d-true"),
                    new LevelPart("line", 3, "d-true"),
                    new LevelPart("line", 3, "d-true"),
            }),
            Map.entry("dL3", new LevelPart[]{
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
                    new LevelPart("line", 4, "d-true"),
            }),
            Map.entry("dT1", new LevelPart[] {
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true"),
                    new LevelPart("triangle", 2, "d-true")
            }),
            Map.entry("dS1", new LevelPart[] {
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true"),
                    new LevelPart("square", 2, "d-true")
            }),
            Map.entry("dC1", new LevelPart[] {
                    new LevelPart("circle", 2, "d-true"),
                    new LevelPart("circle", 2, "d-true"),
                    new LevelPart("circle", 2, "d-true")
            }),
            Map.entry("dC2", new LevelPart[] {
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true"),
                    new LevelPart("circle", 3, "d-true")
            }),

            //DISTINCT TRIANGLE SECTION LEVELS
            //TODO Implement these, all the way through the chain, and the other set of three property levels.
            Map.entry("d,tC", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ft-true", "d-true"}),
                    new LevelPart("circle",new String[]{"sh-3", "ft-true", "d-true"}),
                    new LevelPart("circle",new String[]{"sh-3", "ft-true", "d-true"}),
                    new LevelPart("circle",new String[]{"sh-3", "ft-true", "d-true"}),
            }),

            //TRIANGLE SECTION LEVELS
            Map.entry("tL", new LevelPart[] {
                    new LevelPart("line", 3, "ts-1"),
                    new LevelPart("line", 4, "ts-2"),
                    new LevelPart("line", 5, "ts-3"),
                    new LevelPart("line", 5, "ts-4"),
                    new LevelPart("line", 5, "ts-5"),
                    new LevelPart("line", 6, "ts-6")
            }),
            Map.entry("tT", new LevelPart[] {
                    new LevelPart("triangle", 1, "ts-1"),
                    new LevelPart("triangle", 2, "ts-2"),
                    new LevelPart("triangle", 2, "ts-3"),
                    new LevelPart("triangle", 3, "ts-4"),
                    new LevelPart("triangle", 3, "ts-5"),
                    new LevelPart("triangle", 2, "ts-6")
            }),
            Map.entry("tS", new LevelPart[] {
                    new LevelPart("square", 2, "ts-1"),
                    new LevelPart("square", 2, "ts-2"),
                    new LevelPart("square", 2, "ts-3"),
                    new LevelPart("square", 3, "ts-4"),
                    new LevelPart("square", 2, "ts-5"),
                    new LevelPart("square", 3, "ts-6"),
                    new LevelPart("square", 3, "ts-7"),
                    new LevelPart("square", 2, "ts-8")
            }),
            Map.entry("tC", new LevelPart[] {
                    new LevelPart("circle", 3, "ts-1"),
                    new LevelPart("circle", 3, "ts-2"),
                    new LevelPart("circle", 4, "ts-3"),
                    new LevelPart("circle", 4, "ts-4"),
                    new LevelPart("circle", 4, "ts-5"),
                    new LevelPart("circle", 4, "ts-6"),
                    new LevelPart("circle", 3, "ts-7")
            }),

            //QUADRILATERAL SECTION LEVELS
            Map.entry("qL", new LevelPart[] {
                    new LevelPart("line", 4, "qs-1"),
                    new LevelPart("line", 5, "qs-2"),
                    new LevelPart("line", 5, "qs-3"),
                    new LevelPart("line", 6, "qs-4"),
                    new LevelPart("line", 6, "qs-5"),
                    new LevelPart("line", 6, "qs-6"),
            }),
            Map.entry("qT", new LevelPart[] {
                    new LevelPart("triangle", 2, "qs-1"),
                    new LevelPart("triangle", 3, "qs-2"),
                    new LevelPart("triangle", 2, "qs-3"),
            }),
            Map.entry("qS", new LevelPart[] {
                    new LevelPart("square", 1, "qs-1"),
                    new LevelPart("square", 2, "qs-2"),
                    new LevelPart("square", 3, "qs-3"),
                    new LevelPart("square", 3, "qs-4"),
                    new LevelPart("square", 4, "qs-5"),
                    new LevelPart("square", 4, "qs-6")
            }),
            Map.entry("qC", new LevelPart[] {
                    new LevelPart("circle", 3, "qs-1"),
                    new LevelPart("circle", 3, "qs-2"),
                    new LevelPart("circle", 3, "qs-3"),
                    new LevelPart("circle", 4, "qs-4"),
                    new LevelPart("circle", 4, "qs-5"),
                    new LevelPart("circle", 4, "qs-6")
            }),

            //INTERSECTION LEVELS
            Map.entry("iL", new LevelPart[] {
                    new LevelPart("line", 2, "i-1"),
                    new LevelPart("line", 3, "i-2"),
                    new LevelPart("line", 3, "i-3"),
                    new LevelPart("line", 4, "i-4"),
                    new LevelPart("line", 4, "i-5"),
                    new LevelPart("line", 4, "i-6")
            }),
            Map.entry("iT", new LevelPart[] {
                    new LevelPart("triangle", 2, "i-2"),
                    new LevelPart("triangle", 2, "i-4"),
                    new LevelPart("triangle", 2, "i-6")
            }),
            Map.entry("iS", new LevelPart[] {
                    new LevelPart("square", 2, "i-2"),
                    new LevelPart("square", 2, "i-4"),
                    new LevelPart("square", 2, "i-6"),
                    new LevelPart("square", 2, "i-8")
            }),
            Map.entry("iC", new LevelPart[] {
                    new LevelPart("circle", 2, "i-2"),
                    new LevelPart("circle", 3, "i-4"),
                    new LevelPart("circle", 3, "i-6"),
                    new LevelPart("circle", 4, "i-8"),
                    new LevelPart("circle", 4, "i-10"),
                    new LevelPart("circle", 4, "i-12")
            }),

            //SECTION LEVELS
            Map.entry("sL", new LevelPart[] {
                    new LevelPart("line", 3, "s-1"),
                    new LevelPart("line", 4, "s-2"),
                    new LevelPart("line", 4, "s-3"),
                    new LevelPart("line", 5, "s-4"),
                    new LevelPart("line", 5, "s-5"),
                    new LevelPart("line", 5, "s-6")
            }),
            Map.entry("sT", new LevelPart[] {
                    new LevelPart("triangle", 1, "s-1"),
                    new LevelPart("triangle", 2, "s-2"),
                    new LevelPart("triangle", 2, "s-3"),
                    new LevelPart("triangle", 3, "s-4"),
                    new LevelPart("triangle", 2, "s-5"),
                    new LevelPart("triangle", 3, "s-6"),
                    new LevelPart("triangle", 2, "s-7")
            }),
            Map.entry("sS", new LevelPart[] {
                    new LevelPart("square", 1, "s-1"),
                    new LevelPart("square", 2, "s-2"),
                    new LevelPart("square", 2, "s-3"),
                    new LevelPart("square", 3, "s-4"),
                    new LevelPart("square", 2, "s-5"),
                    new LevelPart("square", 3, "s-6"),
                    new LevelPart("square", 2, "s-7"),
                    new LevelPart("square", 3, "s-8"),
                    new LevelPart("square", 2, "s-9")
            }),
            Map.entry("sC", new LevelPart[] {
                    new LevelPart("circle", 1, "s-1"),
                    new LevelPart("circle", 2, "s-2"),
                    new LevelPart("circle", 2, "s-3"),
                    new LevelPart("circle", 3, "s-4"),
                    new LevelPart("circle", 3, "s-5"),
                    new LevelPart("circle", 4, "s-6"),
                    new LevelPart("circle", 3, "s-7")
            }),

            //INSIDE EDGE LEVELS
            Map.entry("ieL", new LevelPart[] {
                    new LevelPart("line", 4, "ie-1"),
                    new LevelPart("line", 4, "ie-2"),
                    new LevelPart("line", 5, "ie-3"),
                    new LevelPart("line", 5, "ie-4"),
                    new LevelPart("line", 5, "ie-5"),
                    new LevelPart("line", 5, "ie-6")
            }),
            Map.entry("ieT", new LevelPart[] {
                    new LevelPart("triangle", 2, "ie-3"),
                    new LevelPart("triangle", 2, "ie-4"),
                    new LevelPart("triangle", 2, "ie-5"),
                    new LevelPart("triangle", 2, "ie-6"),
            }),
            Map.entry("ieS", new LevelPart[] {
                    new LevelPart("square", 2, "ie-3"),
                    new LevelPart("square", 2, "ie-4"),
                    new LevelPart("square", 2, "ie-5"),
                    new LevelPart("square", 2, "ie-6"),
                    new LevelPart("square", 2, "ie-7"),
                    new LevelPart("square", 2, "ie-8")
            }),
            Map.entry("ieC", new LevelPart[] {
                    new LevelPart("circle", 2, "ie-1"),
                    new LevelPart("circle", 2, "ie-2"),
                    new LevelPart("circle", 3, "ie-3"),
                    new LevelPart("circle", 3, "ie-4"),
                    new LevelPart("circle", 4, "ie-5"),
                    new LevelPart("circle", 3, "ie-6"),
                    new LevelPart("circle", 4, "ie-7"),
                    new LevelPart("circle", 3, "ie-8"),
                    new LevelPart("circle", 3, "ie-9"),
                    new LevelPart("circle", 3, "ie-10")
            }),

            //OUTSIDE EDGE LEVELS
            Map.entry("oeL", new LevelPart[] {
                    new LevelPart("line", 3, "oe-3"),
                    new LevelPart("line", 4, "oe-4"),
                    new LevelPart("line", 4, "oe-5"),
                    new LevelPart("line", 4, "oe-6"),
                    new LevelPart("line", 5, "oe-7"),
                    new LevelPart("line", 5, "oe-8")
            }),
            Map.entry("oeT", new LevelPart[] {
                    new LevelPart("triangle", 1, "oe-3"),
                    new LevelPart("triangle", 2, "oe-6"),
                    new LevelPart("triangle", 2, "oe-7"),
                    new LevelPart("triangle", 2, "oe-9"),
                    new LevelPart("triangle", 3, "oe-10"),
                    new LevelPart("triangle", 3, "oe-11"),
                    new LevelPart("triangle", 2, "oe-12"),
            }),
            Map.entry("oeS", new LevelPart[] {
                    new LevelPart("square", 1, "oe-4"),
                    new LevelPart("square", 2, "oe-7"),
                    new LevelPart("square", 2, "oe-8"),
                    new LevelPart("square", 2, "oe-9"),
                    new LevelPart("square", 2, "oe-10"),
                    new LevelPart("square", 2, "oe-11"),
                    new LevelPart("square", 3, "oe-12"),
                    new LevelPart("square", 2, "oe-13"),
                    new LevelPart("square", 3, "oe-14"),
                    new LevelPart("square", 3, "oe-15"),
                    new LevelPart("square", 2, "oe-16")

            }),
            Map.entry("oeC", new LevelPart[] {
                    new LevelPart("circle", 1, "oe-1"),
                    new LevelPart("circle", 2, "oe-2"),
                    new LevelPart("circle", 3, "oe-3"),
                    new LevelPart("circle", 3, "oe-4"),
                    new LevelPart("circle", 4, "oe-5"),
                    new LevelPart("circle", 4, "oe-6")
            }),

            //TODO Implement these and the other ones.
            //CONTAINED INTERSECTION LEVELS
            Map.entry("ciL", new LevelPart[] {
                    new LevelPart("line", 5, "ci-1"),
                    new LevelPart("line", 6, "ci-2"),
                    new LevelPart("line", 6, "ci-3"),
                    /*
                    new LevelPart("line", 7, "ci-4"),
                    new LevelPart("line", 7, "ci-5"),
                    new LevelPart("line", 7, "ci-6")
                     */
            }),
            Map.entry("ciT", new LevelPart[] {
                    new LevelPart("triangle", 3, "ci-2"),
                    new LevelPart("triangle", 3, "ci-4"),
                    new LevelPart("triangle", 3, "ci-6"),
            }),
            Map.entry("ciS", new LevelPart[] {
                    new LevelPart("square", 3, "ci-2"),
                    new LevelPart("square", 3, "ci-4"),
                    new LevelPart("square", 3, "ci-6"),
                    new LevelPart("square", 3, "ci-8")

            }),
            Map.entry("ciC", new LevelPart[] {
                    new LevelPart("circle", 3, "ci-2"),
                    new LevelPart("circle", 3, "ci-3"),
                    new LevelPart("circle", 3, "ci-4"),
                    new LevelPart("circle", 4, "ci-5"),
                    new LevelPart("circle", 4, "ci-6"),
                    new LevelPart("circle", 4, "ci-7")
            }),

            //TOTAL EDGE LEVELS
            Map.entry("teL", new LevelPart[] {
                    new LevelPart("line", 1, "te-1"),
                    new LevelPart("line", 2, "te-2"),
                    new LevelPart("line", 3, "te-3"),
                    new LevelPart("line", 2, "te-4"),
                    new LevelPart("line", 3, "te-5"),
                    new LevelPart("line", 4, "te-6"),
                    new LevelPart("line", 3, "te-7"),
                    new LevelPart("line", 4, "te-8"),
                    new LevelPart("line", 3, "te-9"),
                    new LevelPart("line", 4, "te-10"),
                    new LevelPart("line", 5, "te-11"),
                    new LevelPart("line", 4, "te-12"),
                    //new LevelPart("line", 5, "te-13"),
                    //new LevelPart("line", 4, "te-14"),
                    //new LevelPart("line", 5, "te-15"),
                    //new LevelPart("line", 4, "te-16")
            }),
            Map.entry("teT", new LevelPart[] {
                    new LevelPart("triangle", 1, "te-3"),
                    new LevelPart("triangle", 2, "te-6"),
                    new LevelPart("triangle", 3, "te-9"),
                    new LevelPart("triangle", 2, "te-10"),
                    new LevelPart("triangle", 4, "te-12"),
                    new LevelPart("triangle", 2, "te-13"),
                    new LevelPart("triangle", 2, "te-14"),
                    new LevelPart("triangle", 5, "te-15"),
                    new LevelPart("triangle", 4, "te-16"),
                    new LevelPart("triangle", 3, "te-17"),
                    new LevelPart("triangle", 2, "te-18")
            }),
            Map.entry("teS", new LevelPart[] {
                    new LevelPart("square", 1, "te-4"),
                    new LevelPart("square", 2, "te-8"),
                    new LevelPart("square", 3, "te-12"),
                    new LevelPart("square", 2, "te-16"),
                    new LevelPart("square", 2, "te-20"),
                    new LevelPart("square", 2, "te-24")

            }),
            Map.entry("teC", new LevelPart[] {
                    new LevelPart("circle", 1, "te-1"),
                    new LevelPart("circle", 2, "te-2"),
                    new LevelPart("circle", 3, "te-3"),
                    new LevelPart("circle", 2, "te-4"),
                    new LevelPart("circle", 3, "te-5"),
                    new LevelPart("circle", 4, "te-6"),
                    new LevelPart("circle", 5, "te-7"),
                    new LevelPart("circle", 3, "te-8"),
                    new LevelPart("circle", 4, "te-9"),
                    new LevelPart("circle", 5, "te-10"),
                    new LevelPart("circle", 6, "te-11"),
                    new LevelPart("circle", 3, "te-12")
            }),

            //CONTAINED INTERSECTION, INSIDE EDGE LEVELS
            Map.entry("ci,ieC1", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-0", "ie-2"}),
                    new LevelPart("circle",new String[]{"ci-0", "ie-4"}),
                    new LevelPart("circle",new String[]{"ci-0", "ie-6"}),
            }),
            Map.entry("ci,ieC2", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-2", "ie-4"}),
                    new LevelPart("circle",new String[]{"ci-2", "ie-6"}),
                    new LevelPart("circle",new String[]{"ci-2", "ie-8"}),
            }),
            Map.entry("ci,ieC3", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-4", "ie-8"}),
                    new LevelPart("circle",new String[]{"ci-4", "ie-10"}),
                    new LevelPart("circle",new String[]{"ci-4", "ie-12"}),
            }),
            Map.entry("ci,ieC4", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-6", "ie-12"}),
                    new LevelPart("circle",new String[]{"ci-6", "ie-14"}),
                    new LevelPart("circle",new String[]{"ci-6", "ie-16"}),
            }),

            //CONTAINED INTERSECTION, TRIANGLE SECTION TUTORIAL LEVEL
            Map.entry("ci,tC", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ci-3", "ts-1"}),
                    new LevelPart("circle",new String[]{"sh-3", "ci-2", "ts-2"}),
                    new LevelPart("circle",new String[]{"sh-3", "ci-4", "ts-2"}),
                    new LevelPart("circle",new String[]{"sh-3", "ci-3", "ts-7"}),

            }),

            //CONTAINED INTERSECTION, TRIANGLE SECTION LEVELS
            Map.entry("sh,ci,tC1", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ci-3", "ts-1"}),
            }),
            Map.entry("sh,ci,tC2", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ci-2", "ts-2"}),
            }),
            Map.entry("sh,ci,tC3", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ci-4", "ts-2"}),
            }),
            Map.entry("sh,ci,tC4", new LevelPart[] {
                    new LevelPart("circle",new String[]{"sh-3", "ci-3", "ts-7"}),
            }),

            //CONTAINED INTERSECTION, TRIANGLE SECTION LEVELS
            Map.entry("ci,tC1", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-2", "ts-1"}),
                    new LevelPart("circle",new String[]{"ci-2", "ts-2"}),
                    new LevelPart("circle",new String[]{"ci-3", "ts-1"}),
            }),
            Map.entry("ci,tC2", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-3", "ts-4"}),
                    new LevelPart("circle",new String[]{"ci-3", "ts-5"}),
                    new LevelPart("circle",new String[]{"ci-3", "ts-6"}),
                    new LevelPart("circle",new String[]{"ci-3", "ts-7"}),
            }),
            Map.entry("ci,tC3", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-4", "ts-2"}),
                    new LevelPart("circle",new String[]{"ci-4", "ts-3"}),
                    new LevelPart("circle",new String[]{"ci-4", "ts-4"}),
            }),
            Map.entry("ci,tC4", new LevelPart[] {
                    new LevelPart("circle",new String[]{"ci-5", "ts-1"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-2"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-3"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-4"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-5"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-6"}),
                    new LevelPart("circle",new String[]{"ci-5", "ts-7"}),
            }),

            Map.entry("the end",new LevelPart[] {
                    new LevelPart("circle",new String[]{"the end-true"}),
            }),
            Map.entry("question",new LevelPart[] {
                    new LevelPart("circle",new String[]{"info text-true"}),
            })
    );
}