package dj.dto.accounts.transaction;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Transactions {

    private List<Booked> booked;

    private List<Pending> pending;

}
