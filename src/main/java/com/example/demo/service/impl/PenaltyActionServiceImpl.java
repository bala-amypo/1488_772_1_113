@Service
public class PenaltyActionServiceImpl implements PenaltyActionService {

    private final PenaltyActionRepository repo;
    private final IntegrityCaseRepository caseRepo;

    public PenaltyActionServiceImpl(PenaltyActionRepository repo,
                                    IntegrityCaseRepository caseRepo) {
        this.repo = repo;
        this.caseRepo = caseRepo;
    }

    public PenaltyAction addPenalty(PenaltyAction p) {
        IntegrityCase c = caseRepo.findById(
            p.getIntegrityCase().getId()
        ).orElseThrow(() -> new ResourceNotFoundException("Case not found"));

        c.setStatus("UNDER_REVIEW");
        caseRepo.save(c);
        return repo.save(p);
    }
}
