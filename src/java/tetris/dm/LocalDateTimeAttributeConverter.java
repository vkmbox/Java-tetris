package tetris.dm;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp>
{

  @Override
  public Timestamp convertToDatabaseColumn(LocalDateTime dateTime)
  {
    return dateTime == null? null: Timestamp.valueOf(dateTime);
  }

  @Override
  public LocalDateTime convertToEntityAttribute(Timestamp timeStamp)
  {
    return timeStamp == null? null: timeStamp.toLocalDateTime();
  }
}