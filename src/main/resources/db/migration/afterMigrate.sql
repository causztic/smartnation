DO $$
/* insert default places */
BEGIN
  IF NOT EXISTS (SELECT 1 from area) THEN
    INSERT INTO area (dtype, latitude, longitude, name) VALUES 
      ('FoodArea', 1.340761, 103.962566, 'Gom Gom');
  END IF;
END;
$$