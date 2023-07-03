-- Trigger
CREATE TRIGGER CALCULATE_SUM_FOR_BILLS_TRIGGER_AFTER_UPDATE
AFTER UPDATE 
ON HOSPITAL.BILL_TABLE
FOR EACH ROW
EXECUTE PROCEDURE HOSPITAL.CALCULATE_SUM_FOR_BILLS_TRIGGER_AFTER_UPDATE_FUNCTION();

-- Trigger Function

CREATE OR REPLACE FUNCTION HOSPITAL.CALCULATE_SUM_FOR_BILLS_TRIGGER_AFTER_UPDATE_FUNCTION()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
	AS
$$
BEGIN
		IF 
		(NEW.consultation_fee <> OLD.consultation_fee) OR 
		(NEW.hospitalization_fee <> OLD.hospitalization_fee) OR
		(NEW.pharmacy_fee <> OLD.pharmacy_fee) THEN
			UPDATE hospital.bill_table
			SET total_amount_of_bill = (NEW.consultation_fee + NEW.hospitalization_fee + NEW.pharmacy_fee);
		END IF;
		
		RETURN NEW;

END;
$$

-- DROP_TRIGGER
DROP TRIGGER CALCULATE_SUM_FOR_BILLS_TRIGGER_AFTER_UPDATE
ON HOSPITAL.BILL_TABLE;

DROP FUNCTION HOSPITAL.CALCULATE_SUM_FOR_BILLS_TRIGGER_AFTER_UPDATE_FUNCTION;

