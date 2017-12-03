DO $$
/* insert default places */
BEGIN
  IF NOT EXISTS (SELECT 1 from area WHERE dtype = 'FoodArea' ) THEN
    INSERT INTO area ( dtype, latitude, longitude, name, image, header_image ) VALUES 
      ('FoodArea', 1.340761, 103.962566, 'Gom Gom', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/gomgom.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/gomgomheader.jpg'),
      ('FoodArea', 1.341139, 103.963067, 'Canteen', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/canteen.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/canteenheader.jpg'),
      ('FoodArea', 1.340798, 103.962096, 'My Nonna''s', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/mynonnas.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/mynonnasheader.jpg'),
      ('FoodArea', 1.340798, 103.962096, 'Tuck Shop', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/tuckshop.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/tuckshopheader.jpg'),
      ('FoodArea', 1.340798, 103.962096, 'Love Pal', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/lovepal.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/lovepalheader.jpg'),
      ('FoodArea', 1.340798, 103.962096, 'Crooked Cooks', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/crookedcooks.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/crookedcooksheader.jpg');
  END IF;

  IF NOT EXISTS (SELECT 1 from area WHERE dtype = 'MeetingArea' ) THEN
    INSERT INTO area ( dtype, latitude, longitude, name,  meeting_category, image, header_image) VALUES 
      ('MeetingArea', 1.340761, 103.962566, 'Library (1st floor)', 'library', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level1.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level1.jpg'),
      ('MeetingArea', 1.340761, 103.962566, 'Library (2nd floor)', 'library', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level2.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level2.jpg'),
      ('MeetingArea', 1.340761, 103.962566, 'Library (3rd floor)', 'library', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level3.jpg', 'https://s3-ap-southeast-1.amazonaws.com/floating-forest/library_level3.jpg');
  END IF;

  IF NOT EXISTS ( SELECT 1 from statistic ) THEN
   /* set some demo data */
    INSERT INTO statistic ( data_date, count, area_id ) VALUES
      (TIMESTAMP '2017-01-01 09:30:00', 10, 1),
      (TIMESTAMP '2017-01-01 10:00:00', 12, 1),      
      (TIMESTAMP '2017-01-01 10:30:00', 15, 1),
      (TIMESTAMP '2017-01-01 11:00:00', 10, 1), 
      (TIMESTAMP '2017-01-01 11:30:00', 15, 1),
      (TIMESTAMP '2017-01-01 12:00:00', 16, 1), 
      (TIMESTAMP '2017-01-01 12:30:00', 10, 1),
      (TIMESTAMP '2017-01-01 13:00:00', 14, 1), 
      (TIMESTAMP '2017-01-01 13:30:00', 20, 1),
      (TIMESTAMP '2017-01-01 14:00:00', 12, 1), 
      (TIMESTAMP '2017-01-01 14:30:00', 14, 1),
      (TIMESTAMP '2017-01-01 15:00:00', 4, 1), 
      (TIMESTAMP '2017-01-01 15:30:00', 9, 1),
      (TIMESTAMP '2017-01-01 16:00:00', 3, 1), 
      (TIMESTAMP '2017-01-01 16:30:00', 10, 1),
      (TIMESTAMP '2017-01-01 17:00:00', 9, 1), 
      (TIMESTAMP '2017-01-01 17:30:00', 8, 1),
      (TIMESTAMP '2017-01-01 18:00:00', 29, 1), 
      (TIMESTAMP '2017-01-01 18:30:00', 8, 1),
      (TIMESTAMP '2017-01-01 19:00:00', 10, 1), 
      (TIMESTAMP '2017-01-01 19:30:00', 3, 1),
      (TIMESTAMP '2017-01-01 20:00:00', 4, 1), 
      (TIMESTAMP '2017-01-01 20:30:00', 2, 1);
  END IF;
END;
$$