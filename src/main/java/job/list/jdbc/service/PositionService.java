package job.list.jdbc.service;

import job.list.domen.Position;

import java.util.Collection;

public interface PositionService {

    Position createPosition(String name);

    void deletePositionById(int id);

    void deletePositionByName(String name);

    Collection<Position> findAllPosition();

    Collection<Position> findAllPositionWithNameLike(String name);

    Collection<Position> findAllPositionById(int id);

    Collection<Position> findPositionByName(String name);


}
