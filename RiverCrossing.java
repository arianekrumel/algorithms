import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

public class RiverCrossing {

    // Create notation of farmer, wolf, sheep and cabbage
    static int farmer = 0;
    static int wolf = 1;
    static int sheep = 2;
    static int cabbage = 3;

    static int[] goal = new int[] { 1, 1, 1, 1 };

    public static ArrayList<int[]> generateStates(boolean withConditions) {

        ArrayList<int[]> states = new ArrayList<int[]>();

        // Generate all valid states
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        int[] thisState = new int[] { i, j, k, l };
                        /*
                         * wolf&sheep and sheep&cabbage combinations cannot be
                         * on opposite side as farmer
                         */
                        if (withConditions) {
                            boolean sheepEaten = false;
                            boolean cabbageEaten = false;
                            if (thisState[farmer] == 0) {
                                sheepEaten = (thisState[sheep] == 1)
                                        && (thisState[wolf] == 1);
                                cabbageEaten = (thisState[sheep] == 1)
                                        && (thisState[cabbage] == 1);
                            }
                            if (thisState[farmer] == 1) {
                                sheepEaten = (thisState[sheep] == 0)
                                        && (thisState[wolf] == 0);
                                cabbageEaten = (thisState[sheep] == 0)
                                        && (thisState[cabbage] == 0);
                            }
                            if (!sheepEaten && !cabbageEaten) {
                                states.add(thisState);
                            }
                        } else {
                            states.add(thisState);
                        }
                    }
                }
            }
        }
        return states;
    }

    public static void displayStates(ArrayList<int[]> allowedStates) {
        // Display all valid states
        for (int i = 0; i < allowedStates.size(); i++) {
            System.out.print("State " + (i) + ": \t");
            for (int j = 0; j < 4; j++) {
                System.out.print(allowedStates.get(i)[j]);
            }
            System.out.print('\n');
        }
    }

    public static TreeMap<Integer, ArrayList<Integer>> findTransitions(
            ArrayList<int[]> allowedStates) {

        TreeMap<Integer, ArrayList<Integer>> transitionTable = new TreeMap<Integer, ArrayList<Integer>>();

        // Find transitions for all states
        for (int i = 0; i < allowedStates.size(); i++) {
            for (int j = 0; j < allowedStates.size(); j++) {
                int diffs = 0;
                int invalidChanges = 0;
                // Farmer changes sides in every state change
                if (allowedStates.get(i)[farmer] == 0
                        && allowedStates.get(j)[farmer] == 1) {
                    for (int k = 1; k < 4; k++) {
                        if (allowedStates.get(i)[k] != allowedStates.get(j)[k]) {
                            diffs++;
                        }
                        if (allowedStates.get(i)[k] == 1
                                && allowedStates.get(j)[k] == 0) {
                            invalidChanges++;
                        }
                    }
                }
                if (allowedStates.get(i)[farmer] == 1
                        && allowedStates.get(j)[farmer] == 0) {
                    for (int k = 1; k < 4; k++) {
                        if (allowedStates.get(i)[k] != allowedStates.get(j)[k]) {
                            diffs++;
                        }
                        if (allowedStates.get(i)[k] == 0
                                && allowedStates.get(j)[k] == 1) {
                            invalidChanges++;
                        }
                    }
                }
                if (allowedStates.get(i)[farmer] != allowedStates.get(j)[farmer]) {
                    if (diffs <= 1 && invalidChanges == 0) {
                        ArrayList<Integer> transitions = new ArrayList<Integer>();
                        if (transitionTable.containsKey(i)) {
                            transitions = transitionTable.get(i);
                            transitionTable.remove(i);
                        }
                        transitions.add(j);
                        transitionTable.put(i, transitions);
                    }
                }
            }
            // Remove all transitions starting at goal node to exclude from search
            if (Arrays.equals(allowedStates.get(i), goal)) {
                transitionTable.remove(i);
            }
        }
        return transitionTable;
    }

    public static int printPath(int goal, TreeMap<Integer, Integer> previous,
            ArrayList<int[]> states, boolean print) {
        int current = goal;
        LinkedList<Integer> shortestPath = new LinkedList<Integer>();
        while (current != -1) {
            shortestPath.addFirst(current);
            current = previous.get(current);
        }
        //System.out.print("\n" + shortestPath.toString() + "\n");
        for (int i = 0; i < shortestPath.size(); i++) {
            if (print) {
                System.out.print("\nstep " + i + ": ");
                outputNice(shortestPath.get(i), states);
            }
        }
        return shortestPath.size() - 1;
    }

    public static void outputNice(int stateIndex, ArrayList<int[]> states) {
        int[] state = states.get(stateIndex);
        for (int i = 0; i < 2; i++) {
            if (state[farmer] == i) {
                System.out.print("f ");
            } else {
                System.out.print("_ ");
            }
            if (state[wolf] == i) {
                System.out.print("w ");
            } else {
                System.out.print("_ ");
            }
            if (state[sheep] == i) {
                System.out.print("s ");
            } else {
                System.out.print("_ ");
            }
            if (state[cabbage] == i) {
                System.out.print("c ");
            } else {
                System.out.print("_ ");
            }
            if (i == 0) {
                System.out.print("| ");
            }

        }

    }

    public static int cost(int child, ArrayList<int[]> states,
            TreeMap<Integer, Integer> explored,
            TreeMap<Integer, Integer> heuristic) {
        int cost = 0;

        // get path cost
        cost += printPath(0, explored, states, false);

        // add h(n)
        cost += heuristic.get(child);

        return cost;
    }

    public static int[] getSmallest(LinkedList<int[]> frontier) {
        int[] current = frontier.pop();
        int[] temp = {};

        for (int i = 0; i < frontier.size(); i++) {
            int[] compare = frontier.pop();
            if (current[1] > compare[1]) {
                temp = current;
                current = compare;
                compare = temp;
            }
            frontier.add(compare);
        }

        return current;
    }

    public static void search(char type, ArrayList<int[]> states,
            TreeMap<Integer, ArrayList<Integer>> transitionTable,
            TreeMap<Integer, Integer> heuristic) {
        int root = 0;
        LinkedList<int[]> frontier = new LinkedList<int[]>();
        LinkedList<Integer> explored = new LinkedList<Integer>();
        TreeMap<Integer, Integer> previous = new TreeMap<Integer, Integer>();

        int[] element = { root, 4 };
        frontier.add(element);
        previous.put(root, -1);

        // while frontier is not empty
        while (frontier.size() > 0) {
            int current = 0;
            if (type == 'a') {
                // Consider heuristic and path cost for A*
                //System.out.print(frontier.size());
                current = getSmallest(frontier)[0];
                //System.out.print(frontier.size());
                //current = frontier.remove(smallest[0])[0];
            }
            if (type == 'b') {
                // Consider path cost for BFS
                current = frontier.pop()[0];
            }
            //System.out.print(current);

            explored.add(current);
            if (Arrays.equals(goal, states.get(current))) {
                printPath(current, previous, states, true);
                return;
            }
            ArrayList<Integer> children = transitionTable.get(current);
            for (int i = 0; i < children.size(); i++) {
                int child = children.get(i);
                if (frontier.indexOf(child) == -1
                        && explored.indexOf(child) == -1) {
                    previous.put(child, current);
                    element[0] = child;
                    if (type == 'a') {
                        element[1] = cost(child, states, previous, heuristic);
                    } else {
                        element[1] = 0;
                    }
                    frontier.add(element);
                }
            }
        }
        return;
    }

    public static TreeMap<Integer, Integer> createHeuristic(
            ArrayList<int[]> states) {
        TreeMap<Integer, Integer> heuristic = new TreeMap<Integer, Integer>();
        int sum = 0;
        // Loop through every state
        for (int i = 0; i < states.size(); i++) {
            for (int j = 0; j < states.get(i).length; j++) {
                sum += states.get(i)[j];
            }
            heuristic.put(i, 4 - sum);
            sum = 0;
        }
        return heuristic;
    }

    public static void main(String[] args) {

        // Generate all allowed states considering conditions
        ArrayList<int[]> statesWithConditions = generateStates(true);
        System.out.println("Allowed states (" + statesWithConditions.size()
                + ")");
        displayStates(statesWithConditions);

        //System.out.println("\n");
        // Find all transitions
        //System.out.println("Transition Table");
        TreeMap<Integer, ArrayList<Integer>> transitionTable = findTransitions(statesWithConditions);
        System.out.println("\nTransition Table\n" + transitionTable.toString()
                + "\n");

        // Create heuristic
        System.out.print("Heuristic Function\n");
        TreeMap<Integer, Integer> heuristic = createHeuristic(statesWithConditions);
        System.out.println("h(n) = " + heuristic + "\n");

        /* Breadth First Search */
        System.out.print("Breadth-first Search");
        search('b', statesWithConditions, transitionTable, heuristic);

        /* A* Search */
        System.out.print("\n\nA* Search");
        search('a', statesWithConditions, transitionTable, heuristic);

    }
}