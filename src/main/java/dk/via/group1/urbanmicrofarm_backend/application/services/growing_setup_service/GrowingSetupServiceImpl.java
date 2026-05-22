package dk.via.group1.urbanmicrofarm_backend.application.services.growing_setup_service;

import dk.via.group1.urbanmicrofarm_backend.application.domain.GrowingSetup;
import dk.via.group1.urbanmicrofarm_backend.database.entities.GrowingSetupEntity;
import dk.via.group1.urbanmicrofarm_backend.database.repository.GrowingSetupRepository;
import dk.via.group1.urbanmicrofarm_backend.database.repository.UserRepository;
import dk.via.group1.urbanmicrofarm_backend.mapper.dbMapper.GrowingSetupDbMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrowingSetupServiceImpl implements GrowingSetupService {

    private final GrowingSetupRepository repository;
    private final GrowingSetupDbMapper dbMapper;
    private final UserRepository userRepository;

    public GrowingSetupServiceImpl(GrowingSetupRepository repository, GrowingSetupDbMapper dbMapper,
                                   UserRepository userRepository) {
        this.repository = repository;
        this.dbMapper = dbMapper;
        this.userRepository = userRepository;
    }

    @Override
    public GrowingSetup assignSetupToUser(int userId, int setupId) {
        GrowingSetupEntity entity = repository.findById(setupId)
                .orElseThrow(() -> new IllegalArgumentException("Growing setup not found with ID: " + setupId));

        entity.setUser(userRepository.getReferenceById((long) userId));
        return dbMapper.toDomain(repository.save(entity));
    }

    @Override
    public GrowingSetup updateSetupLocation(int setupId, String location) {
        GrowingSetupEntity entity = repository.findById(setupId)
                .orElseThrow(() -> new IllegalArgumentException("Growing setup not found with ID: " + setupId));

        entity.setLocation(location);

        return dbMapper.toDomain(repository.save(entity));
    }

    @Override
    public void disconnectSetup(int setupId) {
        GrowingSetupEntity entity = repository.findById(setupId)
                .orElseThrow(() -> new IllegalArgumentException("Growing setup not found with ID: " + setupId));

        entity.setUser(null);
        repository.save(entity);
    }

    @Override
    public List<GrowingSetup> getSetupsForUser(int userId) {
        return repository.findByUser_Id((long) userId).stream()
                .map(dbMapper::toDomain)
                .collect(Collectors.toList());
    }
}
