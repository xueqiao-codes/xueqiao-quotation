cc_library(
    name = 'contract_online',
    srcs = [
        'src/gen/contract_constants.cpp',
        'src/gen/contract_online_dao_service_constants.cpp',
        'src/gen/contract_online_dao_service_types.cpp',
        'src/gen/contract_standard_constants.cpp',
        'src/gen/contract_standard_types.cpp',
        'src/gen/contract_types.cpp',
        'src/gen/ContractOnlineDaoService_variables.cpp',
        'src/gen/ContractOnlineDaoService.cpp',
        'src/gen/client/contract_online_dao_service_stub.cpp',
    ],
    export_incs = [
        'src/gen',
        'src/gen/client',
    ],
    deps = [
        '//cpp_platform/svr:svrcpp',
    ]
)