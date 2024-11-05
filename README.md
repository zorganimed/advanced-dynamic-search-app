advanced dynamic search dependency

1- ajouter la dépendance suivante dans le pom.xml

        <dependency>
            <groupId>io.github.zorganimed</groupId>
            <artifactId>advanced-dynamic-search-app</artifactId>
            <version>1.0.2</version>
        </dependency>

2- ajouter l'annotation @Searchable sur les attributs de recherche de la classe DTO
   exemple :
   	 @Searchable
   	 private String firstName;

3- l'interface repository doit étendre de la linterface JPASearchRepository<Entity_Name> 

    exemple :
	public interface PersonRepository extends JpaRepository<PersonEntity, Long>, JPASearchRepository<PersonEntity> {}

4- céer la méthode de la recherche dynamique avancée comme suit

Dans le controller:

/*http://localhost:8089/api/v1/persons/dynamicSearch?
firstName_endsWith=Ahmed
&lastName_in=Ahmed
&birthDate_lt=19980101
&_offset=0
&_limit=100
&birthDate_sort=ASC*/
    @GetMapping(path="/dynamicSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> findWithPagination(@RequestParam Map<String, String> requestParams) {
        return personService.findWithPagination(requestParams);
    }

Dans le service:

 @Override
    public List<CustomerDTO> findWithPagination(Map<String, String> requestParams) {
        //filters.put("name_eq", "Mohamed");
        //filters.put("lastName_startsWith", "Zor");
        //filters.put("birthDate_gte", "19910101");
        //filters.put("country_in", "IT,FR,DE");
        // filters.put("company.name_in", "Microsoft,Apple");
        // filters.put("company.employees_between", "500,5000");
        return customerRepository.findAllWithPaginationAndSorting(requestParams, CustomerDTO.class)
                .map(c->dtoMapper.fromCustomer(c)).stream().toList();
    } 

pour plus des détails
https://github.com/zorganimed/advanced-dynamic-search
