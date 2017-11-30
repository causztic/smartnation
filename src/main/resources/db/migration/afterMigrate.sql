DO $$
/* insert default places */
BEGIN
  IF NOT EXISTS (SELECT 1 from area) THEN
    INSERT INTO area (dtype, latitude, longitude, name) VALUES 
      ('FoodArea', 1.340761, 103.962566, 'Gom Gom'),
      ('FoodArea', 1.341139, 103.963067, 'Canteen'),
      ('FoodArea', 1.340798, 103.962096, 'My Nonna''s');
  END IF;
END;
$$