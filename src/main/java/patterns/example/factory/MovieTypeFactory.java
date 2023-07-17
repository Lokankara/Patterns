package patterns.example.factory;

import java.util.HashMap;
import java.util.Map;

public class MovieTypeFactory {
    private static MovieTypeFactory instance;
    private Map<String, MovieType> types = new HashMap<>();

    private MovieTypeFactory() {
    }

    public static MovieTypeFactory getInstance() {
        return instance == null ? new MovieTypeFactory() : instance;
    }

    public MovieType create(String type) {
        String caps = type.toLowerCase();
        if (types.containsKey(caps)) {
            return types.get(caps);
        }
        switch (caps) {
            case "Regular" -> types.put(caps, new RegularMovieType());
            case "Child" -> types.put(caps, new ChildMovieType());
            case "NewRelease" -> types.put(caps, new NewReleaseType());

        }
        return types.get(caps);
    }
}
