package team404.project.repository.interfaces;

import team404.project.model.FriendRequest;
import team404.project.model.User;

import java.util.List;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Integer> {
    List<FriendRequest> getByReceiver(User receiver);
    void deleteById(Integer id);
}
