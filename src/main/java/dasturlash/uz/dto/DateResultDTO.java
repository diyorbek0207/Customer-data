package dasturlash.uz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class DateResultDTO<T>{
    private List<T> list;
    private Long total;
}
