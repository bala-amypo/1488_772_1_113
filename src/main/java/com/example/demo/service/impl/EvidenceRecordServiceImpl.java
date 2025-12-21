@Service
public class EvidenceRecordServiceImpl implements EvidenceRecordService {

    private final EvidenceRecordRepository repo;

    public EvidenceRecordServiceImpl(EvidenceRecordRepository repo) {
        this.repo = repo;
    }

    public EvidenceRecord submitEvidence(EvidenceRecord e) {
        if (e.getIntegrityCase() == null)
            throw new IllegalArgumentException("Case required");
        return repo.save(e);
    }
}
