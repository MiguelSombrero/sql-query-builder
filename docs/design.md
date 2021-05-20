# Design document

All the classes extends - either directly or by some other class - `SQLQuery`, which represents SQL query and contains methods of appending values to query string.

If class is 'terminal operation', meaning that the query string is valid SQL and can be executed, class extends `TerminalOperation` class (or equivalent `TerminalSelectOperation` etc.).

## `/src/main/java/factory`

Package contains all the factory classes to create queries and clauses.  

Use factory class `QueryFactory` to build SQL queries. Factory classes `WhereClauseFactory` and `HavingClauseFactory` is used to build `WHERE` and `HAVING` clauses, that can be embedded in SQL queries.

See detailed examples in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## `/src/main/java/validation`

Package contains validators for validating user input.

**Note that** these simple validations is not enough for preventing SQL injection type attacks. If this library is used in untrusted environment, it is highly recommended to use parametrized queries.

Check out examples building of parametrized queries in [Examples](https://github.com/MiguelSombrero/sql-query-builder/tree/develop/docs/examples.md) document.

## `/src/main/java/builder/condition`

Package where classes of `WHERE` and `HAVING` clauses is implemented.

Where and having clauses is used as embedded in another SQL statement by calling `where(Condition whereClause)` and `having(Condition havingClause)` methods.

### Class diagram

![Condition_class_diagram](https://viewer.diagrams.net/?highlight=0000ff&edit=_blank&layers=1&nav=1&title=sql-query-builder-where-class-diagram.drawio#R%3Cmxfile%20pages%3D%222%22%3E%3Cdiagram%20id%3D%22C5RBs43oDa-KdzZeNtuy%22%20name%3D%22Page-1%22%3E7V1bc9o6EP41zPQ8kPENA4%2BBkKQ9aS5NTtP0pSOwwGqMRWyTQH79kXzDtoQxxoZc1Mk01lqWbH27q9VqV2mo%2FenizAEz8zs2oNVQJGPRUE8aiiJ3Wir5RSnLgKJ2Q8LEQUZYaUW4Ra8wJEohdY4M6KYqehhbHpqliSNs23DkpWjAcfBLutoYW%2BleZ2ACGcLtCFgs9R4ZnhlQO0p7RT%2BHaGJGPct6N7gzBVHl8EtcExj4JUFSBw2172DsBVfTRR9adPCicbn%2Fury3Lh71s2837hP4r%2Ffv3eXPZtDY6TaPxJ%2FgQNsr3bSpaK%2BP6uX5z%2Fv%2F5FvJ%2Fa19c%2B1miOUzsObhePWxbSAPYTv8aG8ZjaT7gqYWsEmpN8a2dxvekUgZWGhik%2BsReUHoEMIzdDxEQDgOb3h4RqgjE1nGBVjiOf0M1wOjx6jUM7GDXkmzwCK3ZEIgtx0v5CdFT9W4pU%2BGXTvQJXWuo7GRY9IFcL2wzghbFpi5aOi%2FMK0yBc4E2T3seXgaNYTntgGNsBSD7Rc8Bz%2FG7EOfL4hIiBwdDbhI8GOI0BnEU%2Bg5S1IlvNtU2iH7ReIWcd%2FLinllPaSZScaNiCAUmEnceNzfDyJgwJ6QUYg7bLWkVH%2ByVrA%2FAkmqO2AR5G3gwR4dRzfJiuQi8a0rks%2BgWzCrzjArsI0vvTlhKsJ0ivQ0h87yn4Z6TK7XszFBwkuwrAXH3lqGdWdghOzJhV%2FnRFtRfoQDQUmYPDu2fGYxkWFA22cmD3hgGAvIDCPb80eq1SM%2FZDz70lGr0SIv1CdleVUmP7S645EvIHwHkM9gkDDzC6QMzWG9XMnezHrLNKDbAp%2FksxTi28KrKgy%2B2BHwVgRvS9kfvOfm%2BPny9%2BnTcUfTfvQG6PVX32iqGgfe2%2FlQIFwRwu3OgQVYlhmE703owL4F5i48BSMPh70UMCsUYVZUaFa0lPQ0X7dZoahv06yQHTgbf%2F91gY5%2FPGk9azwd3zTlFsO2%2FuXV%2BMut5xBdQe7hGXSIsRHqpks4AZ9ZNcnFGfAAxgV39pE7DMhwgVzPFdNPVRgf3MKQuwzGNvYGAuZKYT64maGxTou1Cln4LPbhs%2BikJ%2FvOp3RZ4PnrTWfkat55Hz0A50VrPbabkZmVVklfYu0zJXAg98OqH4aJCmikQLzfhCXBNxcZRBnwoG0cU9c11S4WcF00oqrAm0ZqggyAs%2FwVDrpfeEjeOVkkb50so9ICeb%2BieuT6IUFfPUIL0RPbSbyL584IFpgriJKbwM0QKhI0Ur55FtMEiC0OhhHNgRZR789pj36O0rimfJxQUKqe0RidjOIJPj18LOlDz7aUVXWMCgvGhmmpsrUK6yMbLDwYaa81TDi0MJ17CD9FGzSyHhRPEe3fZ5Ykf4acRoW9KK%2BteDp4qChX78Cjx39vv6rDpmeeOS8vljG6nsX1NrJoCQ6Nsd6VHTVtzWJ8a3bU25mW1P2yo8Iqw5w5TRhjezDGtHK2mN7VPrwtxupOfykY2GI3%2FuXnNMPWi%2FabMMP4aBawwyzkKxtW%2BsZk2utjCxNNc2Jjv9IGiKcELAuuML2jkJ80ZQZ3lcVd5WBsgSG0rrEbOB%2FUEyeom8H%2BYPAW9eV0dkeX68rheOue5sByj%2B2l8ORsmIsLe3L0krq6sqUURyMHMK8c7%2F4tAXFZiDsH97uzzrpQki1LSHJVMMtyQX1dnyizOE8cSOw5584EtpDnqoBW9%2Bh95wo0u8eSwFlMz1VirWuHVt5syE4SbKHBKwS7e3BjLBfsK2dAp22hyCvCm4B7aOFmt6ZYvIVCrxJzbY9WGh9zNtSJg7nQ6xViru8zXIL7zizmFnRdYZhXi3P34MYa6xBd4Sz0eIVYFw6erQ9rNhUmgbXQ3xVirR063kTJw1qY5RXD3T64icb6xBm4hTavEvLuoV1qcrsA5EKpVwe5puzRWOO%2BMyfXEbmXcwKyQLU0qq1Du9BUdhOEoIo9AexuwLYP7StT2bWVH13m3iPPXBlfM%2BDRKCaBdFmkW1JB8yvSn9UjzSpmGl0rcK4WZ7XgBFwfzqyqHtHQUGS7AufqcNYLau4qcB6Me3q75b4s%2Fkp%2FbO0aLhcXf5usOA8h%2BWyY8HyScaZGNWlfimkmeTXoCOBLA190UVUF8Pc3d9bD79%2BdxYN2eWmdXN3YV2ZTYRdVyP0awx5EVxLwketdjX%2FSSq6Au3QkoVrQ9K5Nn7ObmRTt3ONF%2Fs7tkYB5K5j1PZpn%2FHdmA4NrzX%2BSJL2RyIA6auvdRqEsqDjtKXguTonKye27hg4io0TTX3bMjMoLqa4lea%2B61KjMuSFMQlPh1KhsS3Ey4b5So9hAuTwWzc8T3ZKbtuOcOnLl6szmzGQ4aVJJFonDMSIOadfGIeeuMjj905VkyZQGj9%2FPzJM%2F36rlkC2zLstkeJbXR3lc9d6SiVtyNQoq044m16eecvJkE7x3B50psoF1RY86EodpvJUDQFtFs3Q%2Bw2kabIiqyODMF%2FGcwLRyMFeXwMl64BjwRAJnSXT3mL%2FJfTuO240uw6NNsMjP9iFltRh0bzVRk7t25BxjtIOpuv3SmDn25khSO%2FkL8foX1NFxc%2B9t%2FaRmZ%2Fzsuqf4aTjtDS1VuILisiVrxZ6DZ6IDxImzB7djsyfO1nwo3Bs9cJbPtmxE%2Foh05q22pAjO86n9%2BU6bzZfy93PaLMe1486nAt%2BK8D34SbOcbQjwPBH4VoTvPo%2BY5b8yu3k8BQuBb0X4ytKhk6U4SRVTZAuAqwK4aDxAfRGbB1yxbmdTv7cNELJ%2BTHuoGYu9%2BPpRT7dU4%2FGVfCZhDfGGolu%2BGy%2FFKvrTnP6Vt3C1FWgFabYg%2F%2FtwSwG96Us6vacl7lEl0QxVBL0XLi3jNsnVJPzt94wSBDClqsNiS19pG2MwgjE5%2FUiqRTI2KNvL0GEoESEOmImfHmbrEtosSzMdOmTR3%2F%2BLvk5e%2F6GlhjjQsfEYr2ub94L19Efrcn2bvBfJKCD%2BTJGZThJzwtiPVgmcFmEcixKWT8EUWVSMzqH1DGmraV22nU7aYZ1fzX7VbrqfndzzdH8UHWQA1%2FTdJoHuT8YGJQKHlPS4pp0tVEVH7iQy75p4gm1gDVbUNd7MaBN%2B09HKdc5DG%2F2dkbZMTVg5%2Fu2DBBDF5%2BhES4nSZyurGxqqbm7ib4%2BxS0z%2BBqfYlc96Mzdsp60XgvVarp05Qb64N%2FPjH6vM%2Brr8TflwHkzMibFh8SGXURu4LkfI34Qfk%2Ft%2BRVZRYo%2B%2BLLx73KTno8tun4HZjNg6%2FOMknjFpTchuQXD3uWfPR1dZh25g5whoy0K7z0OV%2BdCyoVMhtAaeD31DQqBbFt29nqXMh5cNc0S2S740lFxEDOJFJmFR4F0a730eqczHm%2BeG3NZXHfoVmmzOUldX8t0LnFCpVebJkSSrabdDR%2BpscDz4JX70VcYpUM5NnmPNsDDv6lFoKXQIG6nlWKtLhkXuxv8yi7ziPoaMt0KtL4CKP2asp2zknwfKSacjwF1QezpaySfEPMGHG10OsY0f9DDCtk3Xv8Oon2jFUULQAznagQN2k2J2z7i0FEtJAS7oGzyibJgS1FDw9yyopf6MYM48WINEy3RspNU%2FLS3dekXS3eq0j7rKqpt0xo%2FcJrpV6jBvsS%2FRZ%2F2L71n02%2FWIPik6mO74rIbeATPzOzboaAz%2BBw%3D%3D%3C%2Fdiagram%3E%3Cdiagram%20id%3D%22Ow9V6Ym54veHrPqEXKy3%22%20name%3D%22Page-2%22%3ElZG7DsIgFIafhtGkBTWuptbLYDQ6OBpSsJBAT0MxVJ%2FeNhSVdNGJw3f%2Bc0ck0%2B3G0FrsgXGFcMJaRFYI4zTB8%2B7pycOT6SzxoDSSDaIPOMsnD5EDvUvGm0hoAZSVdQwLqCpe2IhRY8DFshuouGpNSz4C54KqMb1IZoWnizBFz7dcliJUTpPBo2kQD6ARlIH7QiRHJDMA1lu6zbjqlxf2oslFXZ1zByd2OL2eiqNeTnyy9T8h7xEMr%2ByvqTvj01r3iQ5M8hc%3D%3C%2Fdiagram%3E%3C%2Fmxfile%3E)

(For the convenience, diagram is showing only one overload of every method of `Comparison` class)

## `/src/main/java/builder/statement/select`

Classes of `SELECT` queries is implemented in package `/main/java/builder/statement/select`.

Create SELECT statement by calling `QueryFactory.select()...` or other select options.

### Class diagram

![Select_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/select-class-diagram.jpg)

## `/src/main/java/builder/statement/insert`

Classes of `INSERT INTO` queries is implemented in package `/main/java/builder/statement/insert`.

Create INSERT statement by calling `QueryFactory.inserInto()...`.

### Class diagram

![Insert_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/insert-class-diagram.jpg)

## `/src/main/java/builder/statement/update`

Classes of `UPDATE` queries is implemented in package `/main/java/builder/statement/update`.

Create UPDATE statement by calling `QueryFactory.update()...`.

### Class diagram

![Update_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/update-class-diagram.jpg)

## `/src/main/java/builder/statement/create`

Classes of `CREATE` queries is implemented in package `/main/java/builder/statement/create`.

Create CREATE statement by calling `QueryFactory.create()...`.

### Class diagram

![Create_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/create-class-diagram.jpg)

## `/src/main/java/builder/statement/delete`

Classes of `DELETE` queries is implemented in package `/main/java/builder/statement/delete`.

Create DELETE statement by calling `QueryFactory.deleteFrom()...`.

### Class diagram

![Delete_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/delete-class-diagram.jpg)

## `/src/main/java/builder/statement/drop`

Classes of `DROP` queries is implemented in package `/main/java/builder/statement/drop`.

Create DROP statement by calling `QueryFactory.drop()...`.

### Class diagram

![Drop_class_diagram](https://github.com/MiguelSombrero/sql-query-builder/blob/develop/docs/drop-class-diagram.jpg)
