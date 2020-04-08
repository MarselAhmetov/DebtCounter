package team404.project.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team404.project.model.FriendRequest;
import team404.project.model.User;
import team404.project.repository.interfaces.FriendRequestRepository;
import team404.project.service.interfaces.FriendRequestService;

import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    @Autowired
    FriendRequestRepository friendRequestRepository;

    public List<FriendRequest> getByReceiver(User receiver) {
        return friendRequestRepository.getByReceiver(receiver);
    }

    public void create(FriendRequest friendRequest) {
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void deleteById(Integer id) {
        friendRequestRepository.deleteById(id);
    }
}
