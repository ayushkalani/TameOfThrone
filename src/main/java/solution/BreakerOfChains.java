package solution;

import java.util.List;
import dto.MessageDTO;

public interface BreakerOfChains {
  List<MessageDTO> generateMessages(List<String> comp);

  List<String> pickCompetitorKingdoms(int n);
}
