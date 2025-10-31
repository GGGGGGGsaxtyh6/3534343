
virtually.mad:     file format elf64-x86-64


Disassembly of section .init:

0000000000001000 <.init>:
    1000:	f3 0f 1e fa          	endbr64
    1004:	48 83 ec 08          	sub    rsp,0x8
    1008:	48 8b 05 c1 2f 00 00 	mov    rax,QWORD PTR [rip+0x2fc1]        # 3fd0 <exit@plt+0x2f10>
    100f:	48 85 c0             	test   rax,rax
    1012:	74 02                	je     1016 <strncpy@plt-0x1a>
    1014:	ff d0                	call   rax
    1016:	48 83 c4 08          	add    rsp,0x8
    101a:	c3                   	ret

Disassembly of section .plt:

0000000000001020 <strncpy@plt-0x10>:
    1020:	ff 35 ca 2f 00 00    	push   QWORD PTR [rip+0x2fca]        # 3ff0 <exit@plt+0x2f30>
    1026:	ff 25 cc 2f 00 00    	jmp    QWORD PTR [rip+0x2fcc]        # 3ff8 <exit@plt+0x2f38>
    102c:	0f 1f 40 00          	nop    DWORD PTR [rax+0x0]

0000000000001030 <strncpy@plt>:
    1030:	ff 25 ca 2f 00 00    	jmp    QWORD PTR [rip+0x2fca]        # 4000 <exit@plt+0x2f40>
    1036:	68 00 00 00 00       	push   0x0
    103b:	e9 e0 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001040 <puts@plt>:
    1040:	ff 25 c2 2f 00 00    	jmp    QWORD PTR [rip+0x2fc2]        # 4008 <exit@plt+0x2f48>
    1046:	68 01 00 00 00       	push   0x1
    104b:	e9 d0 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001050 <strlen@plt>:
    1050:	ff 25 ba 2f 00 00    	jmp    QWORD PTR [rip+0x2fba]        # 4010 <exit@plt+0x2f50>
    1056:	68 02 00 00 00       	push   0x2
    105b:	e9 c0 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001060 <__stack_chk_fail@plt>:
    1060:	ff 25 b2 2f 00 00    	jmp    QWORD PTR [rip+0x2fb2]        # 4018 <exit@plt+0x2f58>
    1066:	68 03 00 00 00       	push   0x3
    106b:	e9 b0 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001070 <printf@plt>:
    1070:	ff 25 aa 2f 00 00    	jmp    QWORD PTR [rip+0x2faa]        # 4020 <exit@plt+0x2f60>
    1076:	68 04 00 00 00       	push   0x4
    107b:	e9 a0 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001080 <calloc@plt>:
    1080:	ff 25 a2 2f 00 00    	jmp    QWORD PTR [rip+0x2fa2]        # 4028 <exit@plt+0x2f68>
    1086:	68 05 00 00 00       	push   0x5
    108b:	e9 90 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

0000000000001090 <strtol@plt>:
    1090:	ff 25 9a 2f 00 00    	jmp    QWORD PTR [rip+0x2f9a]        # 4030 <exit@plt+0x2f70>
    1096:	68 06 00 00 00       	push   0x6
    109b:	e9 80 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

00000000000010a0 <perror@plt>:
    10a0:	ff 25 92 2f 00 00    	jmp    QWORD PTR [rip+0x2f92]        # 4038 <exit@plt+0x2f78>
    10a6:	68 07 00 00 00       	push   0x7
    10ab:	e9 70 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

00000000000010b0 <__isoc99_scanf@plt>:
    10b0:	ff 25 8a 2f 00 00    	jmp    QWORD PTR [rip+0x2f8a]        # 4040 <exit@plt+0x2f80>
    10b6:	68 08 00 00 00       	push   0x8
    10bb:	e9 60 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

00000000000010c0 <exit@plt>:
    10c0:	ff 25 82 2f 00 00    	jmp    QWORD PTR [rip+0x2f82]        # 4048 <exit@plt+0x2f88>
    10c6:	68 09 00 00 00       	push   0x9
    10cb:	e9 50 ff ff ff       	jmp    1020 <strncpy@plt-0x10>

Disassembly of section .text:

00000000000010d0 <.text>:
    10d0:	f3 0f 1e fa          	endbr64
    10d4:	31 ed                	xor    ebp,ebp
    10d6:	49 89 d1             	mov    r9,rdx
    10d9:	5e                   	pop    rsi
    10da:	48 89 e2             	mov    rdx,rsp
    10dd:	48 83 e4 f0          	and    rsp,0xfffffffffffffff0
    10e1:	50                   	push   rax
    10e2:	54                   	push   rsp
    10e3:	45 31 c0             	xor    r8d,r8d
    10e6:	31 c9                	xor    ecx,ecx
    10e8:	48 8d 3d 65 06 00 00 	lea    rdi,[rip+0x665]        # 1754 <exit@plt+0x694>
    10ef:	ff 15 cb 2e 00 00    	call   QWORD PTR [rip+0x2ecb]        # 3fc0 <exit@plt+0x2f00>
    10f5:	f4                   	hlt
    10f6:	66 2e 0f 1f 84 00 00 	cs nop WORD PTR [rax+rax*1+0x0]
    10fd:	00 00 00 
    1100:	48 8d 3d 59 2f 00 00 	lea    rdi,[rip+0x2f59]        # 4060 <exit@plt+0x2fa0>
    1107:	48 8d 05 52 2f 00 00 	lea    rax,[rip+0x2f52]        # 4060 <exit@plt+0x2fa0>
    110e:	48 39 f8             	cmp    rax,rdi
    1111:	74 15                	je     1128 <exit@plt+0x68>
    1113:	48 8b 05 ae 2e 00 00 	mov    rax,QWORD PTR [rip+0x2eae]        # 3fc8 <exit@plt+0x2f08>
    111a:	48 85 c0             	test   rax,rax
    111d:	74 09                	je     1128 <exit@plt+0x68>
    111f:	ff e0                	jmp    rax
    1121:	0f 1f 80 00 00 00 00 	nop    DWORD PTR [rax+0x0]
    1128:	c3                   	ret
    1129:	0f 1f 80 00 00 00 00 	nop    DWORD PTR [rax+0x0]
    1130:	48 8d 3d 29 2f 00 00 	lea    rdi,[rip+0x2f29]        # 4060 <exit@plt+0x2fa0>
    1137:	48 8d 35 22 2f 00 00 	lea    rsi,[rip+0x2f22]        # 4060 <exit@plt+0x2fa0>
    113e:	48 29 fe             	sub    rsi,rdi
    1141:	48 89 f0             	mov    rax,rsi
    1144:	48 c1 ee 3f          	shr    rsi,0x3f
    1148:	48 c1 f8 03          	sar    rax,0x3
    114c:	48 01 c6             	add    rsi,rax
    114f:	48 d1 fe             	sar    rsi,1
    1152:	74 14                	je     1168 <exit@plt+0xa8>
    1154:	48 8b 05 7d 2e 00 00 	mov    rax,QWORD PTR [rip+0x2e7d]        # 3fd8 <exit@plt+0x2f18>
    115b:	48 85 c0             	test   rax,rax
    115e:	74 08                	je     1168 <exit@plt+0xa8>
    1160:	ff e0                	jmp    rax
    1162:	66 0f 1f 44 00 00    	nop    WORD PTR [rax+rax*1+0x0]
    1168:	c3                   	ret
    1169:	0f 1f 80 00 00 00 00 	nop    DWORD PTR [rax+0x0]
    1170:	f3 0f 1e fa          	endbr64
    1174:	80 3d e5 2e 00 00 00 	cmp    BYTE PTR [rip+0x2ee5],0x0        # 4060 <exit@plt+0x2fa0>
    117b:	75 33                	jne    11b0 <exit@plt+0xf0>
    117d:	55                   	push   rbp
    117e:	48 83 3d 5a 2e 00 00 	cmp    QWORD PTR [rip+0x2e5a],0x0        # 3fe0 <exit@plt+0x2f20>
    1185:	00 
    1186:	48 89 e5             	mov    rbp,rsp
    1189:	74 0d                	je     1198 <exit@plt+0xd8>
    118b:	48 8b 3d c6 2e 00 00 	mov    rdi,QWORD PTR [rip+0x2ec6]        # 4058 <exit@plt+0x2f98>
    1192:	ff 15 48 2e 00 00    	call   QWORD PTR [rip+0x2e48]        # 3fe0 <exit@plt+0x2f20>
    1198:	e8 63 ff ff ff       	call   1100 <exit@plt+0x40>
    119d:	c6 05 bc 2e 00 00 01 	mov    BYTE PTR [rip+0x2ebc],0x1        # 4060 <exit@plt+0x2fa0>
    11a4:	5d                   	pop    rbp
    11a5:	c3                   	ret
    11a6:	66 2e 0f 1f 84 00 00 	cs nop WORD PTR [rax+rax*1+0x0]
    11ad:	00 00 00 
    11b0:	c3                   	ret
    11b1:	66 66 2e 0f 1f 84 00 	data16 cs nop WORD PTR [rax+rax*1+0x0]
    11b8:	00 00 00 00 
    11bc:	0f 1f 40 00          	nop    DWORD PTR [rax+0x0]
    11c0:	f3 0f 1e fa          	endbr64
    11c4:	e9 67 ff ff ff       	jmp    1130 <exit@plt+0x70>
    11c9:	55                   	push   rbp
    11ca:	48 89 e5             	mov    rbp,rsp
    11cd:	48 83 ec 10          	sub    rsp,0x10
    11d1:	be 38 00 00 00       	mov    esi,0x38
    11d6:	bf 01 00 00 00       	mov    edi,0x1
    11db:	e8 a0 fe ff ff       	call   1080 <calloc@plt>
    11e0:	48 89 45 f8          	mov    QWORD PTR [rbp-0x8],rax
    11e4:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    11e8:	c7 00 00 00 00 00    	mov    DWORD PTR [rax],0x0
    11ee:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    11f2:	c7 40 04 00 00 00 00 	mov    DWORD PTR [rax+0x4],0x0
    11f9:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    11fd:	c7 40 08 00 00 00 00 	mov    DWORD PTR [rax+0x8],0x0
    1204:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1208:	c7 40 0c 00 00 00 00 	mov    DWORD PTR [rax+0xc],0x0
    120f:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1213:	c7 40 30 00 00 00 00 	mov    DWORD PTR [rax+0x30],0x0
    121a:	48 8b 55 f8          	mov    rdx,QWORD PTR [rbp-0x8]
    121e:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1222:	48 89 50 10          	mov    QWORD PTR [rax+0x10],rdx
    1226:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    122a:	48 8d 50 04          	lea    rdx,[rax+0x4]
    122e:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1232:	48 89 50 18          	mov    QWORD PTR [rax+0x18],rdx
    1236:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    123a:	48 8d 50 08          	lea    rdx,[rax+0x8]
    123e:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1242:	48 89 50 20          	mov    QWORD PTR [rax+0x20],rdx
    1246:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    124a:	48 8d 50 0c          	lea    rdx,[rax+0xc]
    124e:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1252:	48 89 50 28          	mov    QWORD PTR [rax+0x28],rdx
    1256:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    125a:	c9                   	leave
    125b:	c3                   	ret
    125c:	55                   	push   rbp
    125d:	48 89 e5             	mov    rbp,rsp
    1260:	48 83 ec 10          	sub    rsp,0x10
    1264:	48 89 7d f8          	mov    QWORD PTR [rbp-0x8],rdi
    1268:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    126c:	8b 78 30             	mov    edi,DWORD PTR [rax+0x30]
    126f:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1273:	8b 70 0c             	mov    esi,DWORD PTR [rax+0xc]
    1276:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    127a:	8b 48 08             	mov    ecx,DWORD PTR [rax+0x8]
    127d:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1281:	8b 50 04             	mov    edx,DWORD PTR [rax+0x4]
    1284:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1288:	8b 00                	mov    eax,DWORD PTR [rax]
    128a:	41 89 f9             	mov    r9d,edi
    128d:	41 89 f0             	mov    r8d,esi
    1290:	89 c6                	mov    esi,eax
    1292:	48 8d 05 6f 0d 00 00 	lea    rax,[rip+0xd6f]        # 2008 <exit@plt+0xf48>
    1299:	48 89 c7             	mov    rdi,rax
    129c:	b8 00 00 00 00       	mov    eax,0x0
    12a1:	e8 ca fd ff ff       	call   1070 <printf@plt>
    12a6:	90                   	nop
    12a7:	c9                   	leave
    12a8:	c3                   	ret
    12a9:	55                   	push   rbp
    12aa:	48 89 e5             	mov    rbp,rsp
    12ad:	48 83 ec 10          	sub    rsp,0x10
    12b1:	48 89 7d f8          	mov    QWORD PTR [rbp-0x8],rdi
    12b5:	89 75 f4             	mov    DWORD PTR [rbp-0xc],esi
    12b8:	83 7d f4 01          	cmp    DWORD PTR [rbp-0xc],0x1
    12bc:	75 17                	jne    12d5 <exit@plt+0x215>
    12be:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    12c2:	8b 40 30             	mov    eax,DWORD PTR [rax+0x30]
    12c5:	0d 00 00 00 10       	or     eax,0x10000000
    12ca:	89 c2                	mov    edx,eax
    12cc:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    12d0:	89 50 30             	mov    DWORD PTR [rax+0x30],edx
    12d3:	eb 4b                	jmp    1320 <exit@plt+0x260>
    12d5:	83 7d f4 00          	cmp    DWORD PTR [rbp-0xc],0x0
    12d9:	75 2c                	jne    1307 <exit@plt+0x247>
    12db:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    12df:	8b 40 30             	mov    eax,DWORD PTR [rax+0x30]
    12e2:	0d 00 00 00 10       	or     eax,0x10000000
    12e7:	89 c2                	mov    edx,eax
    12e9:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    12ed:	89 50 30             	mov    DWORD PTR [rax+0x30],edx
    12f0:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    12f4:	8b 40 30             	mov    eax,DWORD PTR [rax+0x30]
    12f7:	35 00 00 00 10       	xor    eax,0x10000000
    12fc:	89 c2                	mov    edx,eax
    12fe:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1302:	89 50 30             	mov    DWORD PTR [rax+0x30],edx
    1305:	eb 19                	jmp    1320 <exit@plt+0x260>
    1307:	48 8d 05 33 0d 00 00 	lea    rax,[rip+0xd33]        # 2041 <exit@plt+0xf81>
    130e:	48 89 c7             	mov    rdi,rax
    1311:	e8 8a fd ff ff       	call   10a0 <perror@plt>
    1316:	bf 01 00 00 00       	mov    edi,0x1
    131b:	e8 a0 fd ff ff       	call   10c0 <exit@plt>
    1320:	c9                   	leave
    1321:	c3                   	ret
    1322:	55                   	push   rbp
    1323:	48 89 e5             	mov    rbp,rsp
    1326:	48 83 ec 30          	sub    rsp,0x30
    132a:	48 89 7d d8          	mov    QWORD PTR [rbp-0x28],rdi
    132e:	89 75 d4             	mov    DWORD PTR [rbp-0x2c],esi
    1331:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    1334:	c1 f8 14             	sar    eax,0x14
    1337:	83 e0 0f             	and    eax,0xf
    133a:	89 45 f0             	mov    DWORD PTR [rbp-0x10],eax
    133d:	83 7d f0 01          	cmp    DWORD PTR [rbp-0x10],0x1
    1341:	0f 85 87 00 00 00    	jne    13ce <exit@plt+0x30e>
    1347:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    134a:	c1 f8 10             	sar    eax,0x10
    134d:	83 e0 0f             	and    eax,0xf
    1350:	89 45 f4             	mov    DWORD PTR [rbp-0xc],eax
    1353:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    1356:	c1 f8 0c             	sar    eax,0xc
    1359:	83 e0 0f             	and    eax,0xf
    135c:	89 45 f8             	mov    DWORD PTR [rbp-0x8],eax
    135f:	83 7d f8 00          	cmp    DWORD PTR [rbp-0x8],0x0
    1363:	74 1f                	je     1384 <exit@plt+0x2c4>
    1365:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    1369:	74 19                	je     1384 <exit@plt+0x2c4>
    136b:	48 8d 05 cf 0c 00 00 	lea    rax,[rip+0xccf]        # 2041 <exit@plt+0xf81>
    1372:	48 89 c7             	mov    rdi,rax
    1375:	e8 26 fd ff ff       	call   10a0 <perror@plt>
    137a:	bf 01 00 00 00       	mov    edi,0x1
    137f:	e8 3c fd ff ff       	call   10c0 <exit@plt>
    1384:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    1387:	25 ff 0f 00 00       	and    eax,0xfff
    138c:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    138f:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    1393:	75 20                	jne    13b5 <exit@plt+0x2f5>
    1395:	8b 45 ec             	mov    eax,DWORD PTR [rbp-0x14]
    1398:	c1 f8 08             	sar    eax,0x8
    139b:	89 45 fc             	mov    DWORD PTR [rbp-0x4],eax
    139e:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    13a2:	8b 55 fc             	mov    edx,DWORD PTR [rbp-0x4]
    13a5:	48 63 d2             	movsxd rdx,edx
    13a8:	48 83 c2 02          	add    rdx,0x2
    13ac:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    13b0:	8b 00                	mov    eax,DWORD PTR [rax]
    13b2:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    13b5:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    13b9:	8b 55 f4             	mov    edx,DWORD PTR [rbp-0xc]
    13bc:	48 63 d2             	movsxd rdx,edx
    13bf:	48 83 c2 02          	add    rdx,0x2
    13c3:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    13c7:	8b 55 ec             	mov    edx,DWORD PTR [rbp-0x14]
    13ca:	89 10                	mov    DWORD PTR [rax],edx
    13cc:	eb 19                	jmp    13e7 <exit@plt+0x327>
    13ce:	48 8d 05 6c 0c 00 00 	lea    rax,[rip+0xc6c]        # 2041 <exit@plt+0xf81>
    13d5:	48 89 c7             	mov    rdi,rax
    13d8:	e8 c3 fc ff ff       	call   10a0 <perror@plt>
    13dd:	bf 01 00 00 00       	mov    edi,0x1
    13e2:	e8 d9 fc ff ff       	call   10c0 <exit@plt>
    13e7:	c9                   	leave
    13e8:	c3                   	ret
    13e9:	55                   	push   rbp
    13ea:	48 89 e5             	mov    rbp,rsp
    13ed:	48 83 ec 30          	sub    rsp,0x30
    13f1:	48 89 7d d8          	mov    QWORD PTR [rbp-0x28],rdi
    13f5:	89 75 d4             	mov    DWORD PTR [rbp-0x2c],esi
    13f8:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    13fb:	c1 f8 14             	sar    eax,0x14
    13fe:	83 e0 0f             	and    eax,0xf
    1401:	89 45 f0             	mov    DWORD PTR [rbp-0x10],eax
    1404:	83 7d f0 01          	cmp    DWORD PTR [rbp-0x10],0x1
    1408:	0f 85 9d 00 00 00    	jne    14ab <exit@plt+0x3eb>
    140e:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    1411:	c1 f8 10             	sar    eax,0x10
    1414:	83 e0 0f             	and    eax,0xf
    1417:	89 45 f4             	mov    DWORD PTR [rbp-0xc],eax
    141a:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    141d:	c1 f8 0c             	sar    eax,0xc
    1420:	83 e0 0f             	and    eax,0xf
    1423:	89 45 f8             	mov    DWORD PTR [rbp-0x8],eax
    1426:	83 7d f8 00          	cmp    DWORD PTR [rbp-0x8],0x0
    142a:	74 1f                	je     144b <exit@plt+0x38b>
    142c:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    1430:	74 19                	je     144b <exit@plt+0x38b>
    1432:	48 8d 05 08 0c 00 00 	lea    rax,[rip+0xc08]        # 2041 <exit@plt+0xf81>
    1439:	48 89 c7             	mov    rdi,rax
    143c:	e8 5f fc ff ff       	call   10a0 <perror@plt>
    1441:	bf 01 00 00 00       	mov    edi,0x1
    1446:	e8 75 fc ff ff       	call   10c0 <exit@plt>
    144b:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    144e:	25 ff 0f 00 00       	and    eax,0xfff
    1453:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    1456:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    145a:	75 20                	jne    147c <exit@plt+0x3bc>
    145c:	8b 45 ec             	mov    eax,DWORD PTR [rbp-0x14]
    145f:	c1 f8 08             	sar    eax,0x8
    1462:	89 45 fc             	mov    DWORD PTR [rbp-0x4],eax
    1465:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1469:	8b 55 fc             	mov    edx,DWORD PTR [rbp-0x4]
    146c:	48 63 d2             	movsxd rdx,edx
    146f:	48 83 c2 02          	add    rdx,0x2
    1473:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    1477:	8b 00                	mov    eax,DWORD PTR [rax]
    1479:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    147c:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1480:	8b 55 f4             	mov    edx,DWORD PTR [rbp-0xc]
    1483:	48 63 d2             	movsxd rdx,edx
    1486:	48 83 c2 02          	add    rdx,0x2
    148a:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    148e:	8b 08                	mov    ecx,DWORD PTR [rax]
    1490:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1494:	8b 55 f4             	mov    edx,DWORD PTR [rbp-0xc]
    1497:	48 63 d2             	movsxd rdx,edx
    149a:	48 83 c2 02          	add    rdx,0x2
    149e:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    14a2:	8b 55 ec             	mov    edx,DWORD PTR [rbp-0x14]
    14a5:	01 ca                	add    edx,ecx
    14a7:	89 10                	mov    DWORD PTR [rax],edx
    14a9:	eb 19                	jmp    14c4 <exit@plt+0x404>
    14ab:	48 8d 05 8f 0b 00 00 	lea    rax,[rip+0xb8f]        # 2041 <exit@plt+0xf81>
    14b2:	48 89 c7             	mov    rdi,rax
    14b5:	e8 e6 fb ff ff       	call   10a0 <perror@plt>
    14ba:	bf 01 00 00 00       	mov    edi,0x1
    14bf:	e8 fc fb ff ff       	call   10c0 <exit@plt>
    14c4:	c9                   	leave
    14c5:	c3                   	ret
    14c6:	55                   	push   rbp
    14c7:	48 89 e5             	mov    rbp,rsp
    14ca:	48 83 ec 30          	sub    rsp,0x30
    14ce:	48 89 7d d8          	mov    QWORD PTR [rbp-0x28],rdi
    14d2:	89 75 d4             	mov    DWORD PTR [rbp-0x2c],esi
    14d5:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    14d8:	c1 f8 14             	sar    eax,0x14
    14db:	83 e0 0f             	and    eax,0xf
    14de:	89 45 f0             	mov    DWORD PTR [rbp-0x10],eax
    14e1:	83 7d f0 01          	cmp    DWORD PTR [rbp-0x10],0x1
    14e5:	0f 85 9b 00 00 00    	jne    1586 <exit@plt+0x4c6>
    14eb:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    14ee:	c1 f8 10             	sar    eax,0x10
    14f1:	83 e0 0f             	and    eax,0xf
    14f4:	89 45 f4             	mov    DWORD PTR [rbp-0xc],eax
    14f7:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    14fa:	c1 f8 0c             	sar    eax,0xc
    14fd:	83 e0 0f             	and    eax,0xf
    1500:	89 45 f8             	mov    DWORD PTR [rbp-0x8],eax
    1503:	83 7d f8 00          	cmp    DWORD PTR [rbp-0x8],0x0
    1507:	74 1f                	je     1528 <exit@plt+0x468>
    1509:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    150d:	74 19                	je     1528 <exit@plt+0x468>
    150f:	48 8d 05 2b 0b 00 00 	lea    rax,[rip+0xb2b]        # 2041 <exit@plt+0xf81>
    1516:	48 89 c7             	mov    rdi,rax
    1519:	e8 82 fb ff ff       	call   10a0 <perror@plt>
    151e:	bf 01 00 00 00       	mov    edi,0x1
    1523:	e8 98 fb ff ff       	call   10c0 <exit@plt>
    1528:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    152b:	25 ff 0f 00 00       	and    eax,0xfff
    1530:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    1533:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    1537:	75 20                	jne    1559 <exit@plt+0x499>
    1539:	8b 45 ec             	mov    eax,DWORD PTR [rbp-0x14]
    153c:	c1 f8 08             	sar    eax,0x8
    153f:	89 45 fc             	mov    DWORD PTR [rbp-0x4],eax
    1542:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1546:	8b 55 fc             	mov    edx,DWORD PTR [rbp-0x4]
    1549:	48 63 d2             	movsxd rdx,edx
    154c:	48 83 c2 02          	add    rdx,0x2
    1550:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    1554:	8b 00                	mov    eax,DWORD PTR [rax]
    1556:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    1559:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    155d:	8b 55 f4             	mov    edx,DWORD PTR [rbp-0xc]
    1560:	48 63 d2             	movsxd rdx,edx
    1563:	48 83 c2 02          	add    rdx,0x2
    1567:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    156b:	8b 10                	mov    edx,DWORD PTR [rax]
    156d:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1571:	8b 4d f4             	mov    ecx,DWORD PTR [rbp-0xc]
    1574:	48 63 c9             	movsxd rcx,ecx
    1577:	48 83 c1 02          	add    rcx,0x2
    157b:	48 8b 04 c8          	mov    rax,QWORD PTR [rax+rcx*8]
    157f:	2b 55 ec             	sub    edx,DWORD PTR [rbp-0x14]
    1582:	89 10                	mov    DWORD PTR [rax],edx
    1584:	eb 19                	jmp    159f <exit@plt+0x4df>
    1586:	48 8d 05 b4 0a 00 00 	lea    rax,[rip+0xab4]        # 2041 <exit@plt+0xf81>
    158d:	48 89 c7             	mov    rdi,rax
    1590:	e8 0b fb ff ff       	call   10a0 <perror@plt>
    1595:	bf 01 00 00 00       	mov    edi,0x1
    159a:	e8 21 fb ff ff       	call   10c0 <exit@plt>
    159f:	c9                   	leave
    15a0:	c3                   	ret
    15a1:	55                   	push   rbp
    15a2:	48 89 e5             	mov    rbp,rsp
    15a5:	48 83 ec 10          	sub    rsp,0x10
    15a9:	48 89 7d f8          	mov    QWORD PTR [rbp-0x8],rdi
    15ad:	89 75 f4             	mov    DWORD PTR [rbp-0xc],esi
    15b0:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    15b4:	8b 00                	mov    eax,DWORD PTR [rax]
    15b6:	89 c7                	mov    edi,eax
    15b8:	e8 03 fb ff ff       	call   10c0 <exit@plt>
    15bd:	55                   	push   rbp
    15be:	48 89 e5             	mov    rbp,rsp
    15c1:	48 83 ec 30          	sub    rsp,0x30
    15c5:	48 89 7d d8          	mov    QWORD PTR [rbp-0x28],rdi
    15c9:	89 75 d4             	mov    DWORD PTR [rbp-0x2c],esi
    15cc:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    15cf:	c1 f8 14             	sar    eax,0x14
    15d2:	83 e0 0f             	and    eax,0xf
    15d5:	89 45 f0             	mov    DWORD PTR [rbp-0x10],eax
    15d8:	83 7d f0 01          	cmp    DWORD PTR [rbp-0x10],0x1
    15dc:	0f 85 ad 00 00 00    	jne    168f <exit@plt+0x5cf>
    15e2:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    15e5:	c1 f8 10             	sar    eax,0x10
    15e8:	83 e0 0f             	and    eax,0xf
    15eb:	89 45 f4             	mov    DWORD PTR [rbp-0xc],eax
    15ee:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    15f1:	c1 f8 0c             	sar    eax,0xc
    15f4:	83 e0 0f             	and    eax,0xf
    15f7:	89 45 f8             	mov    DWORD PTR [rbp-0x8],eax
    15fa:	83 7d f8 00          	cmp    DWORD PTR [rbp-0x8],0x0
    15fe:	74 1f                	je     161f <exit@plt+0x55f>
    1600:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    1604:	74 19                	je     161f <exit@plt+0x55f>
    1606:	48 8d 05 34 0a 00 00 	lea    rax,[rip+0xa34]        # 2041 <exit@plt+0xf81>
    160d:	48 89 c7             	mov    rdi,rax
    1610:	e8 8b fa ff ff       	call   10a0 <perror@plt>
    1615:	bf 01 00 00 00       	mov    edi,0x1
    161a:	e8 a1 fa ff ff       	call   10c0 <exit@plt>
    161f:	8b 45 d4             	mov    eax,DWORD PTR [rbp-0x2c]
    1622:	25 ff 0f 00 00       	and    eax,0xfff
    1627:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    162a:	83 7d f8 01          	cmp    DWORD PTR [rbp-0x8],0x1
    162e:	75 20                	jne    1650 <exit@plt+0x590>
    1630:	8b 45 ec             	mov    eax,DWORD PTR [rbp-0x14]
    1633:	c1 f8 08             	sar    eax,0x8
    1636:	89 45 fc             	mov    DWORD PTR [rbp-0x4],eax
    1639:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    163d:	8b 55 fc             	mov    edx,DWORD PTR [rbp-0x4]
    1640:	48 63 d2             	movsxd rdx,edx
    1643:	48 83 c2 02          	add    rdx,0x2
    1647:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    164b:	8b 00                	mov    eax,DWORD PTR [rax]
    164d:	89 45 ec             	mov    DWORD PTR [rbp-0x14],eax
    1650:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1654:	8b 55 f4             	mov    edx,DWORD PTR [rbp-0xc]
    1657:	48 63 d2             	movsxd rdx,edx
    165a:	48 83 c2 02          	add    rdx,0x2
    165e:	48 8b 04 d0          	mov    rax,QWORD PTR [rax+rdx*8]
    1662:	8b 00                	mov    eax,DWORD PTR [rax]
    1664:	39 45 ec             	cmp    DWORD PTR [rbp-0x14],eax
    1667:	75 13                	jne    167c <exit@plt+0x5bc>
    1669:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    166d:	be 01 00 00 00       	mov    esi,0x1
    1672:	48 89 c7             	mov    rdi,rax
    1675:	e8 2f fc ff ff       	call   12a9 <exit@plt+0x1e9>
    167a:	eb 2c                	jmp    16a8 <exit@plt+0x5e8>
    167c:	48 8b 45 d8          	mov    rax,QWORD PTR [rbp-0x28]
    1680:	be 00 00 00 00       	mov    esi,0x0
    1685:	48 89 c7             	mov    rdi,rax
    1688:	e8 1c fc ff ff       	call   12a9 <exit@plt+0x1e9>
    168d:	eb 19                	jmp    16a8 <exit@plt+0x5e8>
    168f:	48 8d 05 ab 09 00 00 	lea    rax,[rip+0x9ab]        # 2041 <exit@plt+0xf81>
    1696:	48 89 c7             	mov    rdi,rax
    1699:	e8 02 fa ff ff       	call   10a0 <perror@plt>
    169e:	bf 01 00 00 00       	mov    edi,0x1
    16a3:	e8 18 fa ff ff       	call   10c0 <exit@plt>
    16a8:	c9                   	leave
    16a9:	c3                   	ret
    16aa:	55                   	push   rbp
    16ab:	48 89 e5             	mov    rbp,rsp
    16ae:	48 83 c4 80          	add    rsp,0xffffffffffffff80
    16b2:	48 89 7d 88          	mov    QWORD PTR [rbp-0x78],rdi
    16b6:	89 75 84             	mov    DWORD PTR [rbp-0x7c],esi
    16b9:	64 48 8b 04 25 28 00 	mov    rax,QWORD PTR fs:0x28
    16c0:	00 00 
    16c2:	48 89 45 f8          	mov    QWORD PTR [rbp-0x8],rax
    16c6:	31 c0                	xor    eax,eax
    16c8:	8b 45 84             	mov    eax,DWORD PTR [rbp-0x7c]
    16cb:	c1 e8 18             	shr    eax,0x18
    16ce:	89 45 9c             	mov    DWORD PTR [rbp-0x64],eax
    16d1:	48 8d 05 4a fc ff ff 	lea    rax,[rip+0xfffffffffffffc4a]        # 1322 <exit@plt+0x262>
    16d8:	48 89 45 a8          	mov    QWORD PTR [rbp-0x58],rax
    16dc:	48 8d 05 06 fd ff ff 	lea    rax,[rip+0xfffffffffffffd06]        # 13e9 <exit@plt+0x329>
    16e3:	48 89 45 b0          	mov    QWORD PTR [rbp-0x50],rax
    16e7:	48 8d 05 d8 fd ff ff 	lea    rax,[rip+0xfffffffffffffdd8]        # 14c6 <exit@plt+0x406>
    16ee:	48 89 45 b8          	mov    QWORD PTR [rbp-0x48],rax
    16f2:	48 8d 05 c4 fe ff ff 	lea    rax,[rip+0xfffffffffffffec4]        # 15bd <exit@plt+0x4fd>
    16f9:	48 89 45 c0          	mov    QWORD PTR [rbp-0x40],rax
    16fd:	48 8d 05 9d fe ff ff 	lea    rax,[rip+0xfffffffffffffe9d]        # 15a1 <exit@plt+0x4e1>
    1704:	48 89 45 c8          	mov    QWORD PTR [rbp-0x38],rax
    1708:	8b 45 9c             	mov    eax,DWORD PTR [rbp-0x64]
    170b:	48 98                	cdqe
    170d:	48 8b 4c c5 a0       	mov    rcx,QWORD PTR [rbp+rax*8-0x60]
    1712:	8b 55 84             	mov    edx,DWORD PTR [rbp-0x7c]
    1715:	48 8b 45 88          	mov    rax,QWORD PTR [rbp-0x78]
    1719:	89 d6                	mov    esi,edx
    171b:	48 89 c7             	mov    rdi,rax
    171e:	ff d1                	call   rcx
    1720:	90                   	nop
    1721:	48 8b 45 f8          	mov    rax,QWORD PTR [rbp-0x8]
    1725:	64 48 2b 04 25 28 00 	sub    rax,QWORD PTR fs:0x28
    172c:	00 00 
    172e:	74 05                	je     1735 <exit@plt+0x675>
    1730:	e8 2b f9 ff ff       	call   1060 <__stack_chk_fail@plt>
    1735:	c9                   	leave
    1736:	c3                   	ret
    1737:	55                   	push   rbp
    1738:	48 89 e5             	mov    rbp,rsp
    173b:	48 8d 05 0e 09 00 00 	lea    rax,[rip+0x90e]        # 2050 <exit@plt+0xf90>
    1742:	48 89 c7             	mov    rdi,rax
    1745:	e8 f6 f8 ff ff       	call   1040 <puts@plt>
    174a:	bf 01 00 00 00       	mov    edi,0x1
    174f:	e8 6c f9 ff ff       	call   10c0 <exit@plt>
    1754:	55                   	push   rbp
    1755:	48 89 e5             	mov    rbp,rsp
    1758:	48 81 ec 40 01 00 00 	sub    rsp,0x140
    175f:	64 48 8b 04 25 28 00 	mov    rax,QWORD PTR fs:0x28
    1766:	00 00 
    1768:	48 89 45 f8          	mov    QWORD PTR [rbp-0x8],rax
    176c:	31 c0                	xor    eax,eax
    176e:	b8 00 00 00 00       	mov    eax,0x0
    1773:	e8 51 fa ff ff       	call   11c9 <exit@plt+0x109>
    1778:	48 89 85 d8 fe ff ff 	mov    QWORD PTR [rbp-0x128],rax
    177f:	48 8d 05 db 08 00 00 	lea    rax,[rip+0x8db]        # 2061 <exit@plt+0xfa1>
    1786:	48 89 c7             	mov    rdi,rax
    1789:	b8 00 00 00 00       	mov    eax,0x0
    178e:	e8 dd f8 ff ff       	call   1070 <printf@plt>
    1793:	48 8d 85 f0 fe ff ff 	lea    rax,[rbp-0x110]
    179a:	48 89 c6             	mov    rsi,rax
    179d:	48 8d 05 d7 08 00 00 	lea    rax,[rip+0x8d7]        # 207b <exit@plt+0xfbb>
    17a4:	48 89 c7             	mov    rdi,rax
    17a7:	b8 00 00 00 00       	mov    eax,0x0
    17ac:	e8 ff f8 ff ff       	call   10b0 <__isoc99_scanf@plt>
    17b1:	48 8d 85 f0 fe ff ff 	lea    rax,[rbp-0x110]
    17b8:	48 89 c7             	mov    rdi,rax
    17bb:	e8 90 f8 ff ff       	call   1050 <strlen@plt>
    17c0:	89 85 cc fe ff ff    	mov    DWORD PTR [rbp-0x134],eax
    17c6:	8b 85 cc fe ff ff    	mov    eax,DWORD PTR [rbp-0x134]
    17cc:	83 e0 07             	and    eax,0x7
    17cf:	85 c0                	test   eax,eax
    17d1:	74 19                	je     17ec <exit@plt+0x72c>
    17d3:	48 8d 05 a4 08 00 00 	lea    rax,[rip+0x8a4]        # 207e <exit@plt+0xfbe>
    17da:	48 89 c7             	mov    rdi,rax
    17dd:	e8 5e f8 ff ff       	call   1040 <puts@plt>
    17e2:	b8 01 00 00 00       	mov    eax,0x1
    17e7:	e9 d5 02 00 00       	jmp    1ac1 <exit@plt+0xa01>
    17ec:	8b 85 cc fe ff ff    	mov    eax,DWORD PTR [rbp-0x134]
    17f2:	8d 50 07             	lea    edx,[rax+0x7]
    17f5:	85 c0                	test   eax,eax
    17f7:	0f 48 c2             	cmovs  eax,edx
    17fa:	c1 f8 03             	sar    eax,0x3
    17fd:	89 85 d0 fe ff ff    	mov    DWORD PTR [rbp-0x130],eax
    1803:	8b 85 d0 fe ff ff    	mov    eax,DWORD PTR [rbp-0x130]
    1809:	89 c6                	mov    esi,eax
    180b:	48 8d 05 7a 08 00 00 	lea    rax,[rip+0x87a]        # 208c <exit@plt+0xfcc>
    1812:	48 89 c7             	mov    rdi,rax
    1815:	b8 00 00 00 00       	mov    eax,0x0
    181a:	e8 51 f8 ff ff       	call   1070 <printf@plt>
    181f:	c7 85 d4 fe ff ff 00 	mov    DWORD PTR [rbp-0x12c],0x0
    1826:	00 00 00 
    1829:	c7 85 c8 fe ff ff 00 	mov    DWORD PTR [rbp-0x138],0x0
    1830:	00 00 00 
    1833:	e9 ef 01 00 00       	jmp    1a27 <exit@plt+0x967>
    1838:	8b 85 c8 fe ff ff    	mov    eax,DWORD PTR [rbp-0x138]
    183e:	c1 e0 03             	shl    eax,0x3
    1841:	48 98                	cdqe
    1843:	48 8d 95 f0 fe ff ff 	lea    rdx,[rbp-0x110]
    184a:	48 8d 0c 02          	lea    rcx,[rdx+rax*1]
    184e:	48 8d 85 e7 fe ff ff 	lea    rax,[rbp-0x119]
    1855:	ba 08 00 00 00       	mov    edx,0x8
    185a:	48 89 ce             	mov    rsi,rcx
    185d:	48 89 c7             	mov    rdi,rax
    1860:	e8 cb f7 ff ff       	call   1030 <strncpy@plt>
    1865:	c6 85 ef fe ff ff 00 	mov    BYTE PTR [rbp-0x111],0x0
    186c:	48 8d 85 e7 fe ff ff 	lea    rax,[rbp-0x119]
    1873:	ba 10 00 00 00       	mov    edx,0x10
    1878:	be 00 00 00 00       	mov    esi,0x0
    187d:	48 89 c7             	mov    rdi,rax
    1880:	e8 0b f8 ff ff       	call   1090 <strtol@plt>
    1885:	89 85 d4 fe ff ff    	mov    DWORD PTR [rbp-0x12c],eax
    188b:	83 bd c8 fe ff ff 04 	cmp    DWORD PTR [rbp-0x138],0x4
    1892:	0f 87 24 01 00 00    	ja     19bc <exit@plt+0x8fc>
    1898:	8b 85 c8 fe ff ff    	mov    eax,DWORD PTR [rbp-0x138]
    189e:	48 8d 14 85 00 00 00 	lea    rdx,[rax*4+0x0]
    18a5:	00 
    18a6:	48 8d 05 6b 08 00 00 	lea    rax,[rip+0x86b]        # 2118 <exit@plt+0x1058>
    18ad:	8b 04 02             	mov    eax,DWORD PTR [rdx+rax*1]
    18b0:	48 98                	cdqe
    18b2:	48 8d 15 5f 08 00 00 	lea    rdx,[rip+0x85f]        # 2118 <exit@plt+0x1058>
    18b9:	48 01 d0             	add    rax,rdx
    18bc:	ff e0                	jmp    rax
    18be:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    18c4:	25 00 00 00 0f       	and    eax,0xf000000
    18c9:	3d 00 00 00 02       	cmp    eax,0x2000000
    18ce:	75 16                	jne    18e6 <exit@plt+0x826>
    18d0:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    18d6:	25 00 00 ff 00       	and    eax,0xff0000
    18db:	3d 00 00 10 00       	cmp    eax,0x100000
    18e0:	0f 84 d9 00 00 00    	je     19bf <exit@plt+0x8ff>
    18e6:	b8 00 00 00 00       	mov    eax,0x0
    18eb:	e8 47 fe ff ff       	call   1737 <exit@plt+0x677>
    18f0:	e9 ca 00 00 00       	jmp    19bf <exit@plt+0x8ff>
    18f5:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    18fb:	25 00 00 00 0f       	and    eax,0xf000000
    1900:	3d 00 00 00 02       	cmp    eax,0x2000000
    1905:	75 16                	jne    191d <exit@plt+0x85d>
    1907:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    190d:	25 ff 0f 00 00       	and    eax,0xfff
    1912:	3d 00 01 00 00       	cmp    eax,0x100
    1917:	0f 84 a5 00 00 00    	je     19c2 <exit@plt+0x902>
    191d:	b8 00 00 00 00       	mov    eax,0x0
    1922:	e8 10 fe ff ff       	call   1737 <exit@plt+0x677>
    1927:	e9 96 00 00 00       	jmp    19c2 <exit@plt+0x902>
    192c:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    1932:	25 00 00 00 0f       	and    eax,0xf000000
    1937:	3d 00 00 00 03       	cmp    eax,0x3000000
    193c:	75 12                	jne    1950 <exit@plt+0x890>
    193e:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    1944:	25 00 00 ff 00       	and    eax,0xff0000
    1949:	3d 00 00 11 00       	cmp    eax,0x110000
    194e:	74 75                	je     19c5 <exit@plt+0x905>
    1950:	b8 00 00 00 00       	mov    eax,0x0
    1955:	e8 dd fd ff ff       	call   1737 <exit@plt+0x677>
    195a:	eb 69                	jmp    19c5 <exit@plt+0x905>
    195c:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    1962:	25 00 00 00 0f       	and    eax,0xf000000
    1967:	3d 00 00 00 01       	cmp    eax,0x1000000
    196c:	75 12                	jne    1980 <exit@plt+0x8c0>
    196e:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    1974:	25 00 00 ff 00       	and    eax,0xff0000
    1979:	3d 00 00 12 00       	cmp    eax,0x120000
    197e:	74 48                	je     19c8 <exit@plt+0x908>
    1980:	b8 00 00 00 00       	mov    eax,0x0
    1985:	e8 ad fd ff ff       	call   1737 <exit@plt+0x677>
    198a:	eb 3c                	jmp    19c8 <exit@plt+0x908>
    198c:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    1992:	25 00 00 00 0f       	and    eax,0xf000000
    1997:	3d 00 00 00 04       	cmp    eax,0x4000000
    199c:	75 12                	jne    19b0 <exit@plt+0x8f0>
    199e:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    19a4:	25 00 00 ff 00       	and    eax,0xff0000
    19a9:	3d 00 00 13 00       	cmp    eax,0x130000
    19ae:	74 1b                	je     19cb <exit@plt+0x90b>
    19b0:	b8 00 00 00 00       	mov    eax,0x0
    19b5:	e8 7d fd ff ff       	call   1737 <exit@plt+0x677>
    19ba:	eb 0f                	jmp    19cb <exit@plt+0x90b>
    19bc:	90                   	nop
    19bd:	eb 0d                	jmp    19cc <exit@plt+0x90c>
    19bf:	90                   	nop
    19c0:	eb 0a                	jmp    19cc <exit@plt+0x90c>
    19c2:	90                   	nop
    19c3:	eb 07                	jmp    19cc <exit@plt+0x90c>
    19c5:	90                   	nop
    19c6:	eb 04                	jmp    19cc <exit@plt+0x90c>
    19c8:	90                   	nop
    19c9:	eb 01                	jmp    19cc <exit@plt+0x90c>
    19cb:	90                   	nop
    19cc:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    19d2:	25 ff 0f 00 00       	and    eax,0xfff
    19d7:	3d 00 01 00 00       	cmp    eax,0x100
    19dc:	7f 19                	jg     19f7 <exit@plt+0x937>
    19de:	8b 95 d4 fe ff ff    	mov    edx,DWORD PTR [rbp-0x12c]
    19e4:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    19eb:	89 d6                	mov    esi,edx
    19ed:	48 89 c7             	mov    rdi,rax
    19f0:	e8 b5 fc ff ff       	call   16aa <exit@plt+0x5ea>
    19f5:	eb 29                	jmp    1a20 <exit@plt+0x960>
    19f7:	8b 85 d4 fe ff ff    	mov    eax,DWORD PTR [rbp-0x12c]
    19fd:	25 ff 0f 00 00       	and    eax,0xfff
    1a02:	89 c2                	mov    edx,eax
    1a04:	8b 85 c8 fe ff ff    	mov    eax,DWORD PTR [rbp-0x138]
    1a0a:	89 c6                	mov    esi,eax
    1a0c:	48 8d 05 95 06 00 00 	lea    rax,[rip+0x695]        # 20a8 <exit@plt+0xfe8>
    1a13:	48 89 c7             	mov    rdi,rax
    1a16:	b8 00 00 00 00       	mov    eax,0x0
    1a1b:	e8 50 f6 ff ff       	call   1070 <printf@plt>
    1a20:	83 85 c8 fe ff ff 01 	add    DWORD PTR [rbp-0x138],0x1
    1a27:	8b 85 c8 fe ff ff    	mov    eax,DWORD PTR [rbp-0x138]
    1a2d:	3b 85 d0 fe ff ff    	cmp    eax,DWORD PTR [rbp-0x130]
    1a33:	0f 8c ff fd ff ff    	jl     1838 <exit@plt+0x778>
    1a39:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a40:	48 89 c7             	mov    rdi,rax
    1a43:	e8 14 f8 ff ff       	call   125c <exit@plt+0x19c>
    1a48:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a4f:	8b 00                	mov    eax,DWORD PTR [rax]
    1a51:	3d 00 02 00 00       	cmp    eax,0x200
    1a56:	75 64                	jne    1abc <exit@plt+0x9fc>
    1a58:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a5f:	8b 40 04             	mov    eax,DWORD PTR [rax+0x4]
    1a62:	83 f8 ff             	cmp    eax,0xffffffff
    1a65:	75 55                	jne    1abc <exit@plt+0x9fc>
    1a67:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a6e:	8b 40 08             	mov    eax,DWORD PTR [rax+0x8]
    1a71:	83 f8 ff             	cmp    eax,0xffffffff
    1a74:	75 46                	jne    1abc <exit@plt+0x9fc>
    1a76:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a7d:	8b 40 0c             	mov    eax,DWORD PTR [rax+0xc]
    1a80:	85 c0                	test   eax,eax
    1a82:	75 38                	jne    1abc <exit@plt+0x9fc>
    1a84:	48 8b 85 d8 fe ff ff 	mov    rax,QWORD PTR [rbp-0x128]
    1a8b:	8b 40 30             	mov    eax,DWORD PTR [rax+0x30]
    1a8e:	3d 00 00 00 10       	cmp    eax,0x10000000
    1a93:	75 27                	jne    1abc <exit@plt+0x9fc>
    1a95:	83 bd d0 fe ff ff 05 	cmp    DWORD PTR [rbp-0x130],0x5
    1a9c:	75 1e                	jne    1abc <exit@plt+0x9fc>
    1a9e:	48 8d 85 f0 fe ff ff 	lea    rax,[rbp-0x110]
    1aa5:	48 89 c6             	mov    rsi,rax
    1aa8:	48 8d 05 29 06 00 00 	lea    rax,[rip+0x629]        # 20d8 <exit@plt+0x1018>
    1aaf:	48 89 c7             	mov    rdi,rax
    1ab2:	b8 00 00 00 00       	mov    eax,0x0
    1ab7:	e8 b4 f5 ff ff       	call   1070 <printf@plt>
    1abc:	b8 00 00 00 00       	mov    eax,0x0
    1ac1:	48 8b 55 f8          	mov    rdx,QWORD PTR [rbp-0x8]
    1ac5:	64 48 2b 14 25 28 00 	sub    rdx,QWORD PTR fs:0x28
    1acc:	00 00 
    1ace:	74 05                	je     1ad5 <exit@plt+0xa15>
    1ad0:	e8 8b f5 ff ff       	call   1060 <__stack_chk_fail@plt>
    1ad5:	c9                   	leave
    1ad6:	c3                   	ret

Disassembly of section .fini:

0000000000001ad8 <.fini>:
    1ad8:	f3 0f 1e fa          	endbr64
    1adc:	48 83 ec 08          	sub    rsp,0x8
    1ae0:	48 83 c4 08          	add    rsp,0x8
    1ae4:	c3                   	ret
