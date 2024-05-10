INSERT INTO public."sec02_categories" VALUES ('CT_TRR', 'Terror') ON CONFLICT("category_id") DO UPDATE SET "name" = excluded."name";
INSERT INTO public."sec02_categories" VALUES ('CT_CYF', 'Ciencia Ficcion') ON CONFLICT("category_id") DO UPDATE SET "name" = excluded."name";
INSERT INTO public."sec02_categories" VALUES ('CT_DRM', 'Drama') ON CONFLICT("category_id") DO UPDATE SET "name" = excluded."name";
INSERT INTO public."sec02_categories" VALUES ('CT_CMD', 'Comedia') ON CONFLICT("category_id") DO UPDATE SET "name" = excluded."name";
INSERT INTO public."sec02_categories" VALUES ('CT_FNT', 'Fantasia') ON CONFLICT("category_id") DO UPDATE SET "name" = excluded."name";
