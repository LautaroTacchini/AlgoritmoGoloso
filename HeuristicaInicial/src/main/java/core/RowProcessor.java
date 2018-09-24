package core;

import java.util.EnumMap;

import org.apache.poi.ss.usermodel.Row;

public interface RowProcessor<K extends Enum<K>>{
	
	public Object process(Row r);
	
	public EnumMap<K, Integer> fillColumnOrder(Row row);

}
