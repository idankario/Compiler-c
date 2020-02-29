
SECTION .TEXT
GLOBAL _start
_start:
call main
mov eax, 1
xor ebx, ebx
int 0x80

main:
push rbp
mov rbp, rsp
pop rax
mov dword [rbp - 8], eax
mov eax, dword [rbp - 8]
push rax
mov rax, 4
push rax
pop rbx
pop rax
mul rax; rbx
push rax
mov eax, dword [rbp - 8]
push rax
mov rsp, rbp
pop rbp
ret