package helpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lachlankitchen
 */
public class ErrorMessage {

   private List<String> violations = new ArrayList();

   public ErrorMessage() {
   }

   public void addError(String message) {
       violations.add(message);
   }
   
   public List<String> getViolations() {
      return violations;
   }

}