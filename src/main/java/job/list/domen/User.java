package job.list.domen;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private final int id;
    private final String name;
    private final String lastName;
    private final String passport;

}
