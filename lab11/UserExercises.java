import java.util.*;
import java.util.stream.Collectors;

public class UserExercises extends DBTable<User> {
    UserExercises() {
    }

    UserExercises(Collection<User> lst) {
        super(lst);
    }

    /**
     * Get an ordered List of Users, sorted first on age,
     * then on their id if the age is the same.
     */
    public List<User> getOrderedByAgeThenId() {
        List<User> rtn = getEntries();
        rtn.sort((a,b)->a.getAge()-b.getAge());
        return rtn; // FIX ME // FIX ME
    }

    /**
     * Get the average age of all the users.
     * If there are no users, the average is 0.
     */
    public double getAverageAge() {
        List<User> rtn = getEntries();
        if(rtn.size() == 0)
            return 0;
        double sum = 0;
        for(int i = 0; i < rtn.size();i++){
            sum += rtn.get(i).getAge();
        }
        sum /= rtn.size();
        return sum; // FIX ME
    }

    /**
     * Group usernames by user age, for all users that have an age greater than min_age.
     * Usernames with ages less than or equal to min_age are excluded.
     * Returns a Map from each age present to a list of the usernames that have that age.
     */
    public Map<Integer, List<String>> groupUsernamesByAgeOlderThan(int min_age) {
        List<User> r = getEntries();
        Map<Integer, List<String>> rtn = new HashMap<Integer, List<String>>();
        for(int i = 0; i < r.size();i++){
            if(r.get(i).getAge() >  min_age){
                List<String> p = new ArrayList<>();
                p.add(r.get(i).getUsername());
                rtn.put(r.get(i).getAge(),p);
            }
        }
        return rtn; // FIX ME
    }
}
