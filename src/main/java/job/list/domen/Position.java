package job.list.domen;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {
    final int id;
    final String name;


    public Position(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
