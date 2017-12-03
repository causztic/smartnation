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
END;
$$